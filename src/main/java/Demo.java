import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

/**
 * Lanterna demo. Uses Java 8.
 */
public class Demo {
    public static void main(String[] args) throws IOException, InterruptedException {

        Terminal terminal = new DefaultTerminalFactory().createTerminal();

        Screen screen = new TerminalScreen(terminal);

        TextGraphics textGraphics = screen.newTextGraphics();

        screen.startScreen();

        // Write text
        textGraphics.putString(10, 10, "Hello World!");

        // draw a rectangle composed of * chars
        textGraphics.drawRectangle(
                new TerminalPosition(3, 3),
                new TerminalSize(10,4),
                '*');

        // draw a triangle composeed of club symbols
        textGraphics.drawTriangle(
                new TerminalPosition(42, 3),
                new TerminalPosition(46, 12),
                new TerminalPosition(50, 3),
                Symbols.CLUB);

        boolean keepRunning = true;

        StringBuilder builder = new StringBuilder();

        while (keepRunning) {
            // Poll input is NON-blocking
            KeyStroke keyPressed = terminal.pollInput();

            if (keyPressed != null) {
                System.out.println(keyPressed);
                switch (keyPressed.getKeyType()) {
                    case Escape:
                        keepRunning = false;
                        break;
                    case ArrowRight:
                        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
                        textGraphics.setBackgroundColor(TextColor.ANSI.GREEN);
                        textGraphics.putString(0, 0, "size of the window: "
                        + screen.getTerminalSize().getColumns()
                                + " x "
                        + screen.getTerminalSize().getRows());
                        screen.refresh();
                        textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
                        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
                        break;
                    case Character:
                        builder.append(keyPressed.getCharacter());
                        break;
                    case Enter:
                        screen.clear();
                        textGraphics.putString(10, 10, builder.toString(),
                                SGR.BOLD);
                        screen.refresh();
                        builder = new StringBuilder();
                        break;
                    case ArrowLeft:
                        int row = 5;
                        int col = 60;
                        for (SGR sgr : SGR.values()) {
                            textGraphics.putString(col, row++, sgr.name(), sgr);
                            System.out.println(sgr.name());
                            screen.refresh();

                        }
                        break;
                    case ArrowUp:
                        textGraphics.setForegroundColor(TextColor.ANSI.MAGENTA);
                        for (int i = 20; i < 40; i++) {
                            textGraphics.putString(i, 20,
                                    String.valueOf(Symbols.BLOCK_SOLID));
                            Thread.sleep(100);
                            screen.refresh();
                        }
                        break;
                    case ArrowDown:
                        screen.setCursorPosition(null); // no cursor
                        for (TextColor.ANSI color : TextColor.ANSI.values()) {
                            textGraphics.setBackgroundColor(color);
                            textGraphics.fill(' ');
                            screen.refresh();
                            Thread.sleep(500);
                        }
                        break;
                    default:
                        System.out.println("default-branch");
                }
            }
        }

        // Need to refresh screen after writing text so the text updates
        screen.refresh();

        // readInput is blocking, meaning the program flow will stop under
        // the user presses a key
        // terminal.readInput();
        screen.stopScreen();
    }
}
