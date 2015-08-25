package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author sh00x.dev
 */

public class SnakePanel extends JPanel {
    private ArrayList<Point> snakeParts;
    private Snake snake;
    private Point apple;

    public SnakePanel() {
        snake = SnakeFrame.getSnakeRunnable();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.ORANGE);
        graphics2D.fillOval(snake.getHeadX(), snake.getHeadY(), Snake.SCALE, Snake.SCALE);

        graphics2D.setColor(Color.RED);
        if (snakeParts != null)
            for (Point part : snakeParts)
                graphics2D.drawOval(part.x, part.y, Snake.SCALE, Snake.SCALE);

        graphics2D.setColor(Color.GREEN);
        graphics2D.fillOval(apple.x, apple.y, Snake.SCALE, Snake.SCALE);

        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        graphics2D.drawString(snake.getScoreString(), 5, 20);

        if (!snake.isStatusPlay())
            graphics2D.drawString("GAME OVER - PRESS SPACE", 5, 40);
    }

    public void setSnake() {
        snake = SnakeFrame.getSnakeRunnable();
    }

    public void setSnakeParts() {
        snakeParts = snake.getSnakeParts();
    }

    public void setApple() {
        apple = snake.getApple();
    }
}
