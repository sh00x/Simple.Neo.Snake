package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * @author sh00x.dev
 */

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            JFrame frame = new SnakeFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("SnakeThreaded");
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
