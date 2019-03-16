package pack1;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents the terminal window.
 */
public class ImportMe {
    private Terminal terminal;
    private Screen screen;
    private TextColor foregroundColor, backgroundColor;
    private TextColor borderColor = TextColor.ANSI.YELLOW;

    private int width, height;
    private boolean isOn;
    private String[] locationDescriptions;
    private int[] posX = {3, 8, 25, 70};
    private String[] labels = {"#", "Label 1", "Label 2", "Label 3"};

    public ImportMe() {
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws IOException {
        terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        foregroundColor = TextColor.ANSI.RED;
        backgroundColor = TextColor.ANSI.BLACK;
    }

    private void drawUI(String header, String footer) throws IOException {

        // Disable cursor visiblity
        screen.setCursorPosition(null);

        // Adjust dimension valuse for zero indexes.
        width = screen.getTerminalSize().getColumns() - 1;
        height = screen.getTerminalSize().getRows() - 1;

        // Clearing the screen, creating header & footer with inverted colors
        screen.clear();

        screen.newTextGraphics().drawLine(0, 0, width, 0,
                new TextCharacter(' ')
                        .withBackgroundColor(foregroundColor)
                        .withForegroundColor(backgroundColor));
        screen.newTextGraphics().drawLine(0, height, width, height,
                new TextCharacter(' ')
                        .withBackgroundColor(foregroundColor)
                        .withForegroundColor(backgroundColor));
        screen.newTextGraphics().setBackgroundColor(foregroundColor)
                .setForegroundColor(backgroundColor)
                .putCSIStyledString(0, 0, header);
        screen.newTextGraphics().setBackgroundColor(foregroundColor)
                .setForegroundColor(backgroundColor)
                .putCSIStyledString(0, height, footer);

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

    private void drawLineBox() {
        screen.newTextGraphics()
                .setForegroundColor(borderColor)
                .setCharacter(0, 1, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
        screen.newTextGraphics()
                .drawLine(1, 1, width - 1, 1,
                        new TextCharacter(Symbols.DOUBLE_LINE_HORIZONTAL)
                                .withForegroundColor(borderColor));
        screen.newTextGraphics().setForegroundColor(borderColor).setCharacter(width, 1, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
        screen.newTextGraphics().setForegroundColor(borderColor).setCharacter(0, 2, Symbols.DOUBLE_LINE_VERTICAL);
        screen.newTextGraphics().setForegroundColor(borderColor).setCharacter(width, 2, Symbols.DOUBLE_LINE_VERTICAL);

        screen.newTextGraphics().setForegroundColor(borderColor).setCharacter(0
                , 3, Symbols.DOUBLE_LINE_T_RIGHT);
        screen.newTextGraphics().drawLine(1, 3, width-1, 3, new TextCharacter(Symbols.DOUBLE_LINE_HORIZONTAL)
                .withForegroundColor(borderColor));
        screen.newTextGraphics().setForegroundColor(borderColor).setCharacter(width, 3, Symbols.DOUBLE_LINE_T_LEFT);

        screen.newTextGraphics().drawLine(0, 4, 0, height-3, new TextCharacter(Symbols.DOUBLE_LINE_VERTICAL)
                .withForegroundColor(borderColor));
        screen.newTextGraphics().drawLine(width, 4, width, height-3, new TextCharacter(Symbols.DOUBLE_LINE_VERTICAL)
                .withForegroundColor(borderColor));

        //DOUBLE_LINE_BOTTOM_LEFT_CORNER
        screen.newTextGraphics().setForegroundColor(borderColor).setCharacter(0,
                height-2, Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
        screen.newTextGraphics().drawLine(1, height-2, width-1, height-2, new TextCharacter(Symbols.DOUBLE_LINE_HORIZONTAL)
                .withForegroundColor(borderColor));
        screen.newTextGraphics().setForegroundColor(borderColor).setCharacter(width,
                height-2, Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

        // Highest temperature statistic
        screen.newTextGraphics().putString(posX[0], height-1, "Highest Temperature Record: ");
    }


    private void typeln(String msg, int col, int row) {
        TextColor defC = foregroundColor;
        foregroundColor = TextColor.ANSI.GREEN;
        int interval = 11;

        for (int i = 0; i < msg.length(); i++) {
            screen.setCursorPosition(new TerminalPosition(col+i, row));
            screen.setCharacter( (col+i), row,
                    new TextCharacter(msg.charAt(i), foregroundColor, backgroundColor));

            try {
                screen.refresh();
                Thread.sleep(ThreadLocalRandom.current().nextInt(interval*3));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        foregroundColor = defC;
    }

    public void start() throws IOException {
        screen.startScreen();
        setup();
        drawUI(" SSI TERMINAL v1.0", " ESC: Quit ");
        terminal.readInput();
        screen.stopScreen();
    }

    private void setup() {
        cursorWait(0, 0, 1111);
        typeln(">_ SENSOR SHADOW INTERFACE READY", 0, 0);
        cursorWait(0, 0, 999);
        typeln(">_ INITIALIZING TEMPERATURE SUPPORT TRANSMUTATOR MATRIX", 0, 4);
        cursorWait(0, 0, 888);
        typeln(">_ ....................................................", 0, 6);
        cursorWait(0, 0, 777);
        typeln(">_ CONNECTING TO SENSOR SYSTEM", 0, 6);
        cursorWait(0, 0, 666);
    }
    private void cursorWait(int col, int row, int millis) {

        screen.setCursorPosition(null);
        //screen.setCursorPosition(new TerminalPosition(col, row));

        try {
            screen.refresh();

            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

