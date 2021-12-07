package com.comert.components;

import java.awt.*;

public final class Ball extends ComponentAbstract implements Movable {

    private final int ballSpeed;
    private final int pointCorrection;
    private final int upCorrection;
    private BallVector ballVector = BallVector.DOWN_LEFT;

    private enum BallVector {
        DOWN, DOWN_LEFT, DOWN_RIGHT,
        UP, UP_LEFT, UP_RIGHT,
        LEFT, RIGHT
    }

    public Ball(int x, int y, int width, int height, ScreenBound screenBound, int ballSpeed) {
        super(x, y, width, height, screenBound);
        this.ballSpeed = ballSpeed;
        pointCorrection = ballSpeed * 10;
        upCorrection = ballSpeed * 7;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.fillOval(x, y, width, height);
    }

    @Override
    public void move() {
        switch (ballVector) {
            case DOWN:
                moveDown();
                break;
            case DOWN_LEFT:
                moveDownLeft();
                break;
            case DOWN_RIGHT:
                moveDownRight();
                break;
            case UP:
                moveUp();
                break;
            case UP_LEFT:
                moveUpLeft();
                break;
            case UP_RIGHT:
                moveUpRight();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;

        }
    }

    @Override
    public void changeVector() {
        switch (ballVector) {
            case UP:
                ballVector = BallVector.UP_RIGHT;
                break;
            case UP_RIGHT:
                ballVector = BallVector.RIGHT;
                break;
            case RIGHT:
                ballVector = BallVector.DOWN_RIGHT;
                break;
            case DOWN_RIGHT:
                ballVector = BallVector.DOWN;
                break;
            case DOWN:
                ballVector = BallVector.DOWN_LEFT;
                break;
            case DOWN_LEFT:
                ballVector = BallVector.LEFT;
                break;
            case LEFT:
                ballVector = BallVector.UP_LEFT;
                break;
            case UP_LEFT:
                ballVector = BallVector.UP;
                break;
        }
    }

    private void moveUpRight() {
        y -= ballSpeed;
        x += ballSpeed;
        if (getTopPoint() <= screenBound.getY() - upCorrection)
            ballVector = BallVector.DOWN_RIGHT;
        if (getRightPoint() >= screenBound.getX() + screenBound.getWidth() - pointCorrection)
            ballVector = BallVector.UP_LEFT;
    }

    private void moveUpLeft() {
        y -= ballSpeed;
        x -= ballSpeed;
        if (getTopPoint() <= screenBound.getY() - upCorrection)
            ballVector = BallVector.DOWN_LEFT;
        if (getLeftPoint() <= screenBound.getX() - pointCorrection)
            ballVector = BallVector.UP_RIGHT;
    }

    private void moveUp() {
        y -= ballSpeed;
        if (getTopPoint() <= screenBound.getY() - upCorrection)
            ballVector = BallVector.DOWN;
    }

    private void moveDownRight() {
        y += ballSpeed;
        x += ballSpeed;
        if (getDownPoint() >= screenBound.getY() + screenBound.getHeight() - pointCorrection)
            ballVector = BallVector.UP_RIGHT;
        if (getRightPoint() >= screenBound.getX() + screenBound.getWidth() - pointCorrection)
            ballVector = BallVector.DOWN_LEFT;
    }

    private void moveDownLeft() {
        y += ballSpeed;
        x -= ballSpeed;
        if (getDownPoint() >= screenBound.getY() + screenBound.getHeight() - pointCorrection)
            ballVector = BallVector.UP_LEFT;
        if (getLeftPoint() <= screenBound.getX() - pointCorrection)
            ballVector = BallVector.DOWN_RIGHT;

    }

    private void moveDown() {
        y += ballSpeed;
        if (getDownPoint() >= screenBound.getY() + screenBound.getHeight() - pointCorrection)
            ballVector = BallVector.UP;
    }

    private void moveLeft() {
        x -= ballSpeed;
        if (getLeftPoint() <= screenBound.getX() - pointCorrection)
            ballVector = BallVector.RIGHT;
    }

    private void moveRight() {
        x += ballSpeed;
        if (getRightPoint() >= screenBound.getX() + screenBound.getWidth() - pointCorrection)
            ballVector = BallVector.LEFT;
    }

    private int getTopPoint() {
        return y;
    }

    private int getDownPoint() {
        return y + height;
    }

    private int getLeftPoint() {
        return x;
    }

    private int getRightPoint() {
        return x + width;
    }

}
