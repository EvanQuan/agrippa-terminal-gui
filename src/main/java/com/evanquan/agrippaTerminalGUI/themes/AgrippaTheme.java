package com.evanquan.agrippaTerminalGUI.themes;

public class AgrippaTheme extends PropertyStringTheme {

    private static final String definition = "# This is the default properties\n" +
            "\n" +
            "foreground = red\n" +
            "background = black\n" +
            "sgr        =\n" +
            "foreground[SELECTED] = red\n" +
            "background[SELECTED] = black\n" +
            "sgr[SELECTED]        = bold\n" +
            "foreground[PRELIGHT] = red\n" +
            "background[PRELIGHT] = black\n" +
            "sgr[PRELIGHT]        = \n" +
            "foreground[ACTIVE]   = red\n" +
            "background[ACTIVE]   = black\n" +
            "sgr[ACTIVE]          = bold\n" +
            "foreground[INSENSITIVE] = red\n" +
            "background[INSENSITIVE] = black\n" +
            "sgr[INSENSITIVE]     =\n" +
            "\n" +
            "# No post-renderer (i.e. no shadows) \n" +
            "postrenderer =\n" +
            "\n" +
            "#Borders\n" +
            "com.googlecode.lanterna.gui2.AbstractBorder.background[PRELIGHT] = red\n" +
            "com.googlecode.lanterna.gui2.AbstractBorder.foreground[ACTIVE] = black\n" +
            "com.googlecode.lanterna.gui2.AbstractBorder.background[ACTIVE] = red\n" +
            "com.googlecode.lanterna.gui2.AbstractBorder.sgr[ACTIVE] =\n" +
            "com.googlecode.lanterna.gui2.AbstractBorder.foreground[INSENSITIVE] = black\n" +
            "com.googlecode.lanterna.gui2.AbstractBorder.background[INSENSITIVE] = red\n" +
            "com.googlecode.lanterna.gui2.Borders$SingleLine.char[HORIZONTAL_LINE] = \\u2500\n" +
            "com.googlecode.lanterna.gui2.Borders$SingleLine.char[VERTICAL_LINE] = \\u2502\n" +
            "com.googlecode.lanterna.gui2.Borders$SingleLine.char[BOTTOM_LEFT_CORNER] = \\u2514\n" +
            "com.googlecode.lanterna.gui2.Borders$SingleLine.char[TOP_LEFT_CORNER] = \\u250c\n" +
            "com.googlecode.lanterna.gui2.Borders$SingleLine.char[BOTTOM_RIGHT_CORNER] = \\u2518\n" +
            "com.googlecode.lanterna.gui2.Borders$SingleLine.char[TOP_RIGHT_CORNER] = \\u2510\n" +
            "com.googlecode.lanterna.gui2.Borders$SingleLine.char[TITLE_LEFT] = \\u2500\n" +
            "com.googlecode.lanterna.gui2.Borders$SingleLine.char[TITLE_RIGHT] = \\u2500\n" +
            "com.googlecode.lanterna.gui2.Borders$DoubleLine.char[HORIZONTAL_LINE] = \\u2550\n" +
            "com.googlecode.lanterna.gui2.Borders$DoubleLine.char[VERTICAL_LINE] = \\u2551\n" +
            "com.googlecode.lanterna.gui2.Borders$DoubleLine.char[BOTTOM_LEFT_CORNER] = \\u255a\n" +
            "com.googlecode.lanterna.gui2.Borders$DoubleLine.char[TOP_LEFT_CORNER] = \\u2554\n" +
            "com.googlecode.lanterna.gui2.Borders$DoubleLine.char[BOTTOM_RIGHT_CORNER] = \\u255d\n" +
            "com.googlecode.lanterna.gui2.Borders$DoubleLine.char[TOP_RIGHT_CORNER] = \\u2557\n" +
            "com.googlecode.lanterna.gui2.Borders$DoubleLine.char[TITLE_LEFT] = \\u2550\n" +
            "com.googlecode.lanterna.gui2.Borders$DoubleLine.char[TITLE_RIGHT] = \\u2550\n" +
            "\n" +
            "#Button\n" +
            "com.googlecode.lanterna.gui2.Button.renderer = com.googlecode.lanterna.gui2.Button$DefaultButtonRenderer\n" +
            "com.googlecode.lanterna.gui2.Button.char[LEFT_BORDER] = <\n" +
            "com.googlecode.lanterna.gui2.Button.char[RIGHT_BORDER] = >\n" +
//            "com.googlecode.lanterna.gui2.Button.cursor = false\n" +
            "\n" +
            "# CheckBox\n" +
            "com.googlecode.lanterna.gui2.CheckBox.sgr[PRELIGHT] = bold\n" +
            "com.googlecode.lanterna.gui2.CheckBox.char[MARKER] = x\n" +
            "\n" +
            "# CheckBoxList\n" +
            "com.googlecode.lanterna.gui2.CheckBoxList.sgr[SELECTED] =\n" +
            "com.googlecode.lanterna.gui2.CheckBoxList.char[LEFT_BRACKET] = [\n" +
            "com.googlecode.lanterna.gui2.CheckBoxList.char[RIGHT_BRACKET] = ]\n" +
            "com.googlecode.lanterna.gui2.CheckBoxList.char[MARKER] = x\n" +
            "\n" +
            "# Default color and style for the window decoration renderer\n" +
            "com.googlecode.lanterna.gui2.DefaultWindowDecorationRenderer.char[HORIZONTAL_LINE] = \\u2500\n" +
            "com.googlecode.lanterna.gui2.DefaultWindowDecorationRenderer.char[VERTICAL_LINE] = \\u2502\n" +
            "com.googlecode.lanterna.gui2.DefaultWindowDecorationRenderer.char[BOTTOM_LEFT_CORNER] = \\u2514\n" +
            "com.googlecode.lanterna.gui2.DefaultWindowDecorationRenderer.char[TOP_LEFT_CORNER] = \\u250c\n" +
            "com.googlecode.lanterna.gui2.DefaultWindowDecorationRenderer.char[BOTTOM_RIGHT_CORNER] = \\u2518\n" +
            "com.googlecode.lanterna.gui2.DefaultWindowDecorationRenderer.char[TOP_RIGHT_CORNER] = \\u2510\n" +
            "com.googlecode.lanterna.gui2.DefaultWindowDecorationRenderer.char[TITLE_SEPARATOR_LEFT] = \\u2500\n" +
            "com.googlecode.lanterna.gui2.DefaultWindowDecorationRenderer.char[TITLE_SEPARATOR_RIGHT] = \\u2500\n" +
            "com.googlecode.lanterna.gui2.DefaultWindowDecorationRenderer.property[TITLE_PADDING] = false\n" +
            "com.googlecode.lanterna.gui2.DefaultWindowDecorationRenderer.property[CENTER_TITLE] = false\n" +
            "\n" +
            "# GUI Backdrop\n" +
            "com.googlecode.lanterna.gui2.GUIBackdrop.foreground = red\n" +
            "com.googlecode.lanterna.gui2.GUIBackdrop.background = black\n" +
            "com.googlecode.lanterna.gui2.GUIBackdrop.sgr = bold\n" +
            "\n" +
            "# List boxes default\n" +
            "com.googlecode.lanterna.gui2.AbstractListBox.foreground[INSENSITIVE] = red\n" +
            "com.googlecode.lanterna.gui2.AbstractListBox.background[INSENSITIVE] = black\n" +
            "\n" +
            "# ProgressBar\n" +
            "com.googlecode.lanterna.gui2.ProgressBar.foreground = red\n" +
            "com.googlecode.lanterna.gui2.ProgressBar.background = black\n" +
            "com.googlecode.lanterna.gui2.ProgressBar.sgr = bold\n" +
            "com.googlecode.lanterna.gui2.ProgressBar.background[ACTIVE] = red\n" +
            "com.googlecode.lanterna.gui2.ProgressBar.foreground[ACTIVATE] = red\n" +
            "com.googlecode.lanterna.gui2.ProgressBar.sgr[ACTIVE] =\n" +
            "com.googlecode.lanterna.gui2.ProgressBar.char[FILLER] =\n" +
            "\n" +
            "# RadioBoxList\n" +
            "com.googlecode.lanterna.gui2.RadioBoxList.sgr[SELECTED] =\n" +
            "com.googlecode.lanterna.gui2.RadioBoxList.char[LEFT_BRACKET] = <\n" +
            "com.googlecode.lanterna.gui2.RadioBoxList.char[RIGHT_BRACKET] = >\n" +
            "com.googlecode.lanterna.gui2.RadioBoxList.char[MARKER] = o\n" +
            "\n" +
            "# ScrollBar\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[UP_ARROW]=\\u25b2\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[DOWN_ARROW]=\\u25bc\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[LEFT_ARROW]=\\u25c4\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[RIGHT_ARROW]=\\u25ba\n" +
            "\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[VERTICAL_BACKGROUND]=\\u2592\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[VERTICAL_SMALL_TRACKER]=\\u2588\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[VERTICAL_TRACKER_BACKGROUND]=\\u2588\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[VERTICAL_TRACKER_TOP]=\\u2588\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[VERTICAL_TRACKER_BOTTOM]=\\u2588\n" +
            "\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[HORIZONTAL_BACKGROUND]=\\u2592\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[HORIZONTAL_SMALL_TRACKER]=\\u2588\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[HORIZONTAL_TRACKER_BACKGROUND]=\\u2588\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[HORIZONTAL_TRACKER_LEFT]=\\u2588\n" +
            "com.googlecode.lanterna.gui2.ScrollBar.char[HORIZONTAL_TRACKER_RIGHT]=\\u2588\n" +
            "\n" +
            "# Separator\n" +
            "com.googlecode.lanterna.gui2.Separator.sgr = bold\n" +
            "\n" +
            "# Table\n" +
            "com.googlecode.lanterna.gui2.table.Table.sgr[HEADER] = underline,bold\n" +
            "com.googlecode.lanterna.gui2.table.Table.foreground[SELECTED] = black\n" +
            "com.googlecode.lanterna.gui2.table.Table.background[SELECTED] = red\n" +
            "com.googlecode.lanterna.gui2.table.Table.sgr[SELECTED] =\n" +
            "\n" +
            "# TextBox\n" +
            "com.googlecode.lanterna.gui2.TextBox.foreground = red\n" +
            "com.googlecode.lanterna.gui2.TextBox.background = black\n" +
            "\n" +
            "# Window shadow\n" +
            "com.googlecode.lanterna.gui2.WindowShadowRenderer.background = black\n" +
            "com.googlecode.lanterna.gui2.WindowShadowRenderer.sgr = bold\n" +
            "com.googlecode.lanterna.gui2.WindowShadowRenderer.property[DOUBLE_WIDTH] = true\n" +
            "com.googlecode.lanterna.gui2.WindowShadowRenderer.property[TRANSPARENT] = false";

    public AgrippaTheme() {
        super(definition);
    }
}
