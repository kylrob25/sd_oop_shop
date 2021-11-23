package me.krob.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.Arrays;
import java.util.List;

public class TableUtil {
    private static final List<Class<?>> CLASSES = Arrays.asList(String.class, Integer.class, Double.class);
    private static final DefaultTableCellRenderer RENDERER = new DefaultTableCellRenderer();

    static {
        RENDERER.setHorizontalAlignment(JLabel.CENTER);
    }

    public static void setCentreRenderer(JTable table) {
        CLASSES.forEach(clazz -> table.setDefaultRenderer(clazz, RENDERER));
    }
}
