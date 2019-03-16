package com.evanquan.agrippaTerminalGUI;

public class TestGUI extends TerminalGUI {

    public TestGUI() {

        leftHeader = "Left Header";
        rightHeader = "Right Header";
        leftFooter = "Left Footer";
        rightFooter = "Right Footer";
    }
    @Override
    protected void setup() {
        cursorHide(1111);
        typeln(">_ SENSOR SHADOW INTERFACE READY", 0, 0);
        cursorHide(999);
        typeln(">_ INITIALIZING TEMPERATURE SUPPORT TRANSMUTATOR MATRIX", 0, 4);
        cursorHide(888);
        typeln(">_ ....................................................", 0, 6);
        cursorHide(777);
        typeln(">_ CONNECTING TO SENSOR SYSTEM", 0, 6);
        cursorHide(666);
    }

}
