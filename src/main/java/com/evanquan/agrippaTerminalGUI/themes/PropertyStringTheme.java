package com.evanquan.agrippaTerminalGUI.themes;

import com.googlecode.lanterna.graphics.PropertyTheme;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public abstract class PropertyStringTheme extends PropertyTheme {

    private static Properties definitionAsProperty(String definition) {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(definition));
            return properties;
        } catch (IOException e) {
            // We should never get here!
            throw new RuntimeException("Unexcepted I/O error", e);
        }
    }

    public PropertyStringTheme(String definition) {
        super(definitionAsProperty(definition), false);
    }
}
