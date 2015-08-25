package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author sh00x.dev
 */

public class SnakeFrame extends JFrame implements KeyListener {
    public static final int SIZE_X = 600;
    public static final int SIZE_Y = 600;

    public static final int MOVE_FORWARD = 0;
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 3;
    public static final int MOVE_BACK = 4;

    private static SnakePanel snakePanel;
    private static Snake snakeRunnable;
    private static int direction;

    public SnakeFrame() {
        setSize(SIZE_X, SIZE_Y);
        addKeyListener(this);
        startGame();
    }

    public void startGame() {
        direction = MOVE_BACK;
        snakePanel = new SnakePanel();
        snakePanel.setBackground(Color.BLACK);
        snakeRunnable = new Snake(snakePanel);
        snakePanel.setSnake();
        snakePanel.setSnakeParts();
        snakePanel.setApple();
        add(snakePanel);

        Thread thread = new Thread(snakeRunnable);
        thread.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W && direction != MOVE_BACK)
            direction = MOVE_FORWARD;
        if (e.getKeyCode() == KeyEvent.VK_A && direction != MOVE_RIGHT)
            direction = MOVE_LEFT;
        if (e.getKeyCode() == KeyEvent.VK_S && direction != MOVE_FORWARD)
            direction = MOVE_BACK;
        if (e.getKeyCode() == KeyEvent.VK_D && direction != MOVE_LEFT)
            direction = MOVE_RIGHT;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!snakeRunnable.isStatusPlay()) {
                remove(snakePanel);
                startGame();
                snakePanel.revalidate();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static int getDirection() {
        return direction;
    }

    public static Snake getSnakeRunnable() {
        return snakeRunnable;
    }
}
