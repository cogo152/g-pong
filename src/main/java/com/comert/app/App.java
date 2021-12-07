package com.comert.app;

import com.comert.components.Ball;
import com.comert.components.Drawable;
import com.comert.components.Movable;
import com.comert.components.ScreenBound;
import com.comert.timer.GameListener;
import com.comert.timer.GameTimer;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class App extends Frame implements GameListener {

    private final Drawable drawableBall;
    private final Movable movableBall;

    public App() throws HeadlessException, InterruptedException {
        final ScreenBound screenBound = new ScreenBound(100, 100, 1024, 768);

        final int gameSpeed = 30;
        final GameTimer timer = new GameTimer(this, gameSpeed);

        this.setBounds(screenBound.getX(), screenBound.getY(), screenBound.getWidth(), screenBound.getHeight());
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timer.stopGame();
                dispose();
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_P:
                        timer.pauseGame();
                        break;
                    case KeyEvent.VK_R:
                        timer.resumeGame();
                        break;
                    case KeyEvent.VK_C:
                        movableBall.changeVector();
                        break;
                }
            }
        });

        final int ballSpeed = 10;
        final Ball ball = new Ball(screenBound.getWidth() / 2, screenBound.getY(), 50, 50, screenBound, ballSpeed);
        drawableBall = ball;
        movableBall = ball;

        this.setVisible(true);

        timer.startGame(500);
        timer.join(); // main thread joins
    }

    @Override
    public void paint(Graphics graphics) {
        drawableBall.draw(graphics);
    }

    @Override
    public void actionPerformed() {
        movableBall.move();
        repaint();
    }

    public static void main(String[] args) throws InterruptedException {
        new App();
        System.out.println("Exiting");
    }

}
