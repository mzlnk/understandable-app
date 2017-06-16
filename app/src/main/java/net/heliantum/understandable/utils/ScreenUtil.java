package net.heliantum.understandable.utils;

/**
 * Created by Marcin on 2017-04-07.
 */

public class ScreenUtil {

    public int width;
    public int height;

    public double widthRatio;
    public double heightRatio;

    private static ScreenUtil screen;

    public static void init(int width, int height) {
        screen = new ScreenUtil(width, height);
    }

    public static ScreenUtil getScreen() {
        return screen;
    }

    public ScreenUtil(int width, int height) {
        this.width = width;
        this.height = height;
        this.widthRatio = (double) width / 1080D;
        this.heightRatio = (double) height / 1920D;
    }

}
