package com.comert.components;

abstract class ComponentAbstract implements Drawable {

    protected int x, y;
    protected final int width, height;
    protected final ScreenBound screenBound;

    protected ComponentAbstract(int x, int y, int width, int height, ScreenBound screenBound) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.screenBound = screenBound;
    }

}
