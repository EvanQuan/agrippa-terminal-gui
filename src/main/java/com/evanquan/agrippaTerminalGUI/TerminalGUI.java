package com.evanquan.agrippaTerminalGUI;

import com.evanquan.agrippaTerminalGUI.themes.AgrippaTheme;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.SimpleTerminalResizeListener;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import com.sun.deploy.panel.ControlPanel;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents the terminal GUI.
 */
public abstract class TerminalGUI {
    private WindowBasedTextGUI textGUI;
    private Terminal terminal;
    private Screen screen;
    private TextGraphics tg;
    private TextColor foregroundColor, backgroundColor;
    private TextColor borderColor = TextColor.ANSI.YELLOW;

    private int width, height;
    private boolean isOn;
    private String[] locationDescriptions;
    private int[] posX = {3, 8, 25, 70};
    private String[] labels = {"#", "Label 1", "Label 2", "Label 3"};

    /**
     * Header string
     */
    protected String rightHeader = "";
    protected String leftHeader = "";
    /**
     * Footer string
     */
    protected String rightFooter = "";
    protected String leftFooter = "";

    protected TerminalGUI() {
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws IOException {
        terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        textGUI = new MultiWindowTextGUI(screen, backgroundColor);

        textGUI.setTheme(new AgrippaTheme());

        TerminalSize size = new TerminalSize(width, height);
//        textGUI.setTheme(LanternaThemes.getRegisteredTheme("businessmachine"));
        Window window = new BasicWindow("My Root Window");
        window.setHints(Arrays.asList(new Window.Hint[]{Window.Hint.EXPANDED}));
//        window

        terminal.addResizeListener(new TerminalResizeListener() {
            @Override
            public void onResized(Terminal terminal, TerminalSize terminalSize) {
                drawHeaders();
                drawFooters();
            }
        });


        Panel contentPanel = new Panel(new GridLayout(2));

        GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
        gridLayout.setHorizontalSpacing(3);

        Label title = new Label("This is a label than spans two columns");

        title.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.BEGINNING, // Horizontal alignment in the grid cell if the cell is larger than the component's preferred size
                GridLayout.Alignment.BEGINNING, // Vertical alignment in the grid cell if the cell is larger than the component's preferred size
               true,       // Give the component extra horizontal space if available
               false,        // Give the component extra vertical space if available
               2,                  // Horizontal span
               1));                  // Vertical span

        contentPanel.addComponent(title);

        contentPanel.addComponent(new Label("Text Box (aligned"));
        contentPanel.addComponent(
                new TextBox()
                .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.CENTER))
        );

        // password box
        contentPanel.addComponent(new Label("Password Box (right aligned"));
        contentPanel.addComponent(
                new TextBox()
                .setMask('*')
                .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.CENTER))
        );

        // ComboBox (one read-only, one editable)
        contentPanel.addComponent(new Label("Read-only Combo box (forced " +
                "sized)"));
        List<String> timezonesAsStrings = new ArrayList<String>();

        for(String id: TimeZone.getAvailableIDs()) {
            timezonesAsStrings.add(id);
        }

        ComboBox<String> readOnlyComboBox = new ComboBox<String>(timezonesAsStrings);
        readOnlyComboBox.setReadOnly(true);
        readOnlyComboBox.setPreferredSize(new TerminalSize(20, 1));
        contentPanel.addComponent(readOnlyComboBox);

        contentPanel.addComponent(new Label("Editable Combo Box (filled)"));
        contentPanel.addComponent(
                new ComboBox<String>("Item #1", "Item #2", "Item #3", "Item #4")
                        .setReadOnly(false)
                        .setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));


        // Button
        contentPanel.addComponent(new Label("Button (centered)"));
        contentPanel.addComponent(new Button("Button", new Runnable() {
            @Override
            public void run() {
                MessageDialog.showMessageDialog(textGUI, "MessageBox", "This is a message box", MessageDialogButton.OK);
            }
        }).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER, GridLayout.Alignment.CENTER)));
        tg = screen.newTextGraphics();
        foregroundColor = TextColor.ANSI.RED;
        backgroundColor = TextColor.ANSI.BLACK;


        // empty row and separator
        contentPanel.addComponent(
                new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(2)));
        contentPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(2)));
        contentPanel.addComponent(
                new Button("Close", new Runnable() {
                    @Override
                    public void run() {
                        window.close();
                    }
                }).setLayoutData(
                        GridLayout.createHorizontallyEndAlignedLayoutData(2)));

        // ALl components added
        window.setComponent(contentPanel);

        textGUI.addWindowAndWait(window);

        drawHeaders();
        drawFooters();

    }

    private void drawUI() throws IOException {

        // Disable cursor visiblity
        screen.setCursorPosition(null);

        // Adjust dimension values for zero indexes.
        // TODO redo with resize callback
        screen.doResizeIfNecessary();
        width = screen.getTerminalSize().getColumns() - 1;
        height = screen.getTerminalSize().getRows() - 1;

        // Clearing the screen, creating header & footer with inverted colors
        screen.clear();

        drawHeaders();
        drawFooters();

        // Key a key-value data structure for mapping data descriptions to
        // screen
        Map<Integer, String> colSetup = new HashMap<>();
        for (int i = 0; i < posX.length; i++) {
            colSetup.put(posX[i], labels[i]);
        }

        int row = 2; // row value is common

        // Iterating over HashMap and casting Lanterna Spell;
        colSetup.forEach( (k,v) -> screen.newTextGraphics().putString(k, row, v));

        // Highest temperature statistic
        drawLineBox();

        screen.refresh();


    }

    private void drawHeaders() {
        // Header background
        tg.drawLine(0, 0, width, 0,
                new TextCharacter(' ')
                        .withBackgroundColor(foregroundColor)
                        .withForegroundColor(backgroundColor));

        // Header text
        tg.setBackgroundColor(foregroundColor)
                .setForegroundColor(backgroundColor)
                .putCSIStyledString(0, 0, leftHeader);

        int offset = width - rightHeader.length();
        if (offset < 0) {
            offset = 0;
        }
        tg.setBackgroundColor(foregroundColor)
                .setForegroundColor(backgroundColor)
                .putCSIStyledString(offset, 0, rightHeader);
    }
    private void drawFooters() {
        tg.drawLine(0, height, width, height,
                new TextCharacter(' ')
                        .withBackgroundColor(foregroundColor)
                        .withForegroundColor(backgroundColor));

        // Footer text
        tg.setBackgroundColor(foregroundColor)
                .setForegroundColor(backgroundColor)
                //.putCSIStyledString(height, 0, leftFooter);
                .putCSIStyledString(0, height, leftFooter);

        int offset = width - rightHeader.length();
        if (offset < 0) {
            offset = 0;
        }
        tg.setBackgroundColor(foregroundColor)
                .setForegroundColor(backgroundColor)
                .putCSIStyledString(offset, height, rightFooter);
    }

    private void drawLineBox() {
        tg.setBackgroundColor(backgroundColor);
        tg
                .setForegroundColor(borderColor)
                .setCharacter(0, 1, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
        tg
                .drawLine(1, 1, width - 1, 1,
                        new TextCharacter(Symbols.DOUBLE_LINE_HORIZONTAL)
                                .withForegroundColor(borderColor));
        tg.setForegroundColor(borderColor).setCharacter(width, 1,
                Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
        tg.setForegroundColor(borderColor).setCharacter(0, 2, Symbols.DOUBLE_LINE_VERTICAL);
        tg.setForegroundColor(borderColor).setCharacter(width, 2, Symbols.DOUBLE_LINE_VERTICAL);

        tg.setForegroundColor(borderColor).setCharacter(0
                , 3, Symbols.DOUBLE_LINE_T_RIGHT);
        tg.drawLine(1, 3, width-1, 3, new TextCharacter(Symbols.DOUBLE_LINE_HORIZONTAL)
                .withForegroundColor(borderColor));
        tg.setForegroundColor(borderColor).setCharacter(width, 3, Symbols.DOUBLE_LINE_T_LEFT);

        tg.drawLine(0, 4, 0, height-3, new TextCharacter(Symbols.DOUBLE_LINE_VERTICAL)
                .withForegroundColor(borderColor));
        tg.drawLine(width, 4, width, height-3, new TextCharacter(Symbols.DOUBLE_LINE_VERTICAL)
                .withForegroundColor(borderColor));

        //DOUBLE_LINE_BOTTOM_LEFT_CORNER
        tg.setForegroundColor(borderColor).setCharacter(0,
                height-2, Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
        tg.drawLine(1, height-2, width-1, height-2, new TextCharacter(Symbols.DOUBLE_LINE_HORIZONTAL)
                .withForegroundColor(borderColor));
        tg.setForegroundColor(borderColor).setCharacter(width,
                height-2, Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

        // Highest temperature statistic
        tg.putString(posX[0], height-1, "Highest Temperature Record: ");
    }


    /**
     * Simulate the computer typing a message on the screen. The message is
     * drawn character by character over time with a cursor to simulate typing.
     *
     * @param msg to type
     * @param col to start typing
     * @param row to start typing
     */
    protected void typeln(String msg, int col, int row) {
        TextColor defC = foregroundColor;
        foregroundColor = TextColor.ANSI.GREEN;
        int interval = 11;

        for (int i = 0; i < msg.length(); i++) {
            screen.setCursorPosition(new TerminalPosition(col + i, row));
            screen.setCharacter( col + i, row,
                    new TextCharacter(msg.charAt(i), foregroundColor, backgroundColor));

            try {
                screen.refresh();
                Thread.sleep(ThreadLocalRandom.current().nextInt(interval*3));
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        foregroundColor = defC;
    }

    public void start() throws IOException {
//        screen.startScreen();
//        setup();
        do {
            drawUI();
        } while (!shouldStopScreen());
        screen.stopScreen();
    }

    /**
     * Check if the screen should be stopped.
     *
     * @return true if the screen should stop, else false
     * @throws IOException if pollInput interrupted
     */
    private boolean shouldStopScreen() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null) {
            switch (keyStroke.getKeyType()) {
                case Escape:
                case EOF:
                    return true;
            }
        }
        return false;
    }

    /**
     * Runs immediately after screen.startScreen. Can use to create into
     * messages before normal window rendering.
     *
     * Default behaviour, do nothing.
     */
    protected void setup() {}

    /**
     * Hide the cursor for a specified amount of time
     *
     * @param millis to hide cursor
     */
    protected void cursorHide(int millis) {
         screen.setCursorPosition(null);
        try {
            screen.refresh();

            Thread.sleep(millis);
        } catch (InterruptedException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    /**
     * Set the cursor to a specified location and have it wait there for a
     * specified time.
     *
     * @param col of cursor
     * @param row of cursor
     * @param millis to wait
     */
    protected void cursorWait(int col, int row, int millis) {

        screen.setCursorPosition(new TerminalPosition(col, row));

        try {
            screen.refresh();

            Thread.sleep(millis);
        } catch (InterruptedException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

