package me.krob.menu;

import me.krob.Main;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Menu extends JFrame {
    protected static final ScheduledExecutorService SCHEDULER = Executors.newSingleThreadScheduledExecutor();

    protected final Main main;

    public Menu(String title, Main main) {
        super(title);
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 250));
        setResizable(false);
        pack();
    }

    public abstract JLabel getDisplayLabel();

    public void updateDisplay(String text) {
        getDisplayLabel().setText(text);
        clearDisplay();
    }

    public void clearDisplay() {
        SCHEDULER.schedule(() -> getDisplayLabel().setText(null), 2, TimeUnit.SECONDS);
    }
}
