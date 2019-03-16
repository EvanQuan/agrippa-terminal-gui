import com.evanquan.agrippaTerminalGUI.TerminalWindow;
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
        TerminalWindow w = new TerminalWindow();

        w.start();
    }
}
