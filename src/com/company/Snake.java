package com.company;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author sh00x.dev
 */

public class Snake implements Runnable {
    public static final int SCALE = 10;
    public static final int BOUND = 52;
    public static final int[] levelsArray = {
            50, 200, 400, 600, 800, 1000, 1200
    };
    private static Random random;

    private ArrayList<Point> snakeParts;
    private SnakePanel snakePanel;
    private boolean statusPlay;
    private int snakeLength;
    private Point head;
    private Point apple;
    private int score;
    private int speed;
    private long timeStart;
    private long timeStop;
    private int level;

    public Snake(SnakePanel snakePanel) {
        timeStart = Instant.now().toEpochMilli() / 1000;

        snakeParts = new ArrayList<>();
        snakeLength = 10;
        speed = 50;
        score = 0;
        level = 0;

        head = new Point(SCALE, SCALE);
        this.snakePanel = snakePanel;
        random = new Random();
        statusPlay = true;

        apple = new Point(getRandomCoordinate(BOUND), getRandomCoordinate(BOUND));
    }

    public void move(int direction) {
        timeStop = Instant.now().toEpochMilli() / 1000;
        int x = (int) head.getX();
        int y = (int) head.getY();
        int prevX = x;
        int prevY = y;

        if (head.equals(apple)) {
            apple.setLocation(getRandomCoordinate(BOUND), getRandomCoordinate(BOUND));
            snakeLength++;
            score += 15;
            if (score >= levelsArray[level]) {
                level++;
                speed -= 5;
            }
        }

        if (direction == SnakeFrame.MOVE_FORWARD) {
            checkStatus(x, y);
            y -= SCALE;
            head.setLocation(x, y);
        }
        if (direction == SnakeFrame.MOVE_LEFT) {
            checkStatus(x, y);
            x -= SCALE;
            head.setLocation(x, y);
        }
        if (direction == SnakeFrame.MOVE_RIGHT) {
            checkStatus(x, y);
            x += SCALE;
            head.setLocation(x, y);
        }
        if (direction == SnakeFrame.MOVE_BACK) {
            checkStatus(x, y);
            y += SCALE;
            head.setLocation(x, y);
        }
        snakeParts.add(new Point(prevX, prevY));
        checkCannibal();

        if (snakeParts.size() > snakeLength)
            snakeParts.remove(snakeParts.size() - (snakeLength + 1));
    }

    @Override
    public void run() {
        while (statusPlay) {
            int direction = SnakeFrame.getDirection();
            move(direction);
            snakePanel.repaint();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception");
            }
        }
    }

    public void checkStatus(int x, int y) {
        //I have no idea, why this Panel is smaller than 600 x 600 (on my computer 594 x 571)
        if (x <= -2 || x >= 592 - SCALE) {
            statusPlay = false;
        }
        if (y <= -2 || y >= 572 - SCALE) {
            statusPlay = false;
        }
    }

    //If u want, u can put getRandomX() and getRandomY() to common "getRandom(int bound) + min"
    public static int getRandomCoordinate(int bound) {
        int a = random.nextInt(bound) + 1;
        int b = 10;
        return a * b;
    }

    private void checkCannibal() {
        snakeParts.stream().filter(head::equals).forEach(parts -> statusPlay = false);
    }

    public String getScoreString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SCORE: ").append(score).append("   ");
        builder.append("LENGTH: ").append(snakeLength).append("   ");
        builder.append("TIME: ").append(timeStop - timeStart).append("   ");
        builder.append("SLOWDOWN: ").append(speed);
        return builder.toString();
    }

    public int getHeadX() {
        return (int) head.getX();
    }

    public int getHeadY() {
        return (int) head.getY();
    }

    public boolean isStatusPlay() {
        return statusPlay;
    }

    public ArrayList<Point> getSnakeParts() {
        return snakeParts;
    }

    public Point getApple() {
        return apple;
    }

}
