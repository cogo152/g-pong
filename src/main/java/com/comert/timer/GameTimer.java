package com.comert.timer;

public final class GameTimer extends Thread {

    private volatile boolean isRunning;
    private volatile boolean isSuspended;

    private final GameListener gameListener;
    private final int gameSpeed;

    public GameTimer(GameListener gameListener, int gameSpeed) {
        this.gameListener = gameListener;
        this.gameSpeed = gameSpeed;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        System.out.println("Started");
        while (isRunning) {
            try {
                Thread.sleep(gameSpeed);
                synchronized (this) {
                    fireAction();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopped");
    }

    private void fireAction() {
        if (isSuspended) {
            try {
                System.out.println("Paused");
                wait();
                System.out.println("Resumed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameListener.actionPerformed();
    }

    public void startGame() {
        if (this.getState() == Thread.State.NEW) {
            isRunning = true;
            System.out.println("Starting");
            this.start();
        }
    }

    public void startGame(long timeout) throws InterruptedException {
        Thread.sleep(timeout);
        startGame();
    }

    public void pauseGame() {
        if (!isSuspended) {
            isSuspended = true;
            System.out.println("Pausing");
        }
    }

    public void resumeGame() {
        if (isSuspended)
            synchronized (this) {
                isSuspended = false;
                System.out.println("Resuming");
                notifyAll();
            }
    }

    public void stopGame() {
        System.out.println("Stopping");
        isRunning = false;
    }

}
