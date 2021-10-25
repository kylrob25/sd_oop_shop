package me.krob.menu;

import me.krob.Main;

import javax.swing.*;
import java.awt.*;

public abstract class Menu extends JFrame {
    protected final Main main;

    public Menu(String title, Main main) {
        super(title);
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 250));
        setResizable(false);
        pack();
    }
}
