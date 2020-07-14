/*
JB Ladera
4th Period
Yee
 */

import java.awt.*;

/**
 * A class to represent an an analog clock
 */
public class Clock {
    private int x, y, size;
    private int x_shorthand, y_shorthand, x_longhand, y_longhand;
    private double t1, t2;

    /**
     * Creates a clock at a specified x and y coordinate and size
     *
     * @param x    the top left x coordinate of the clock
     * @param y    the top left y coordinate of the clock
     * @param size the width and height of the clock
     */
    public Clock(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        x_shorthand = y_shorthand = x_longhand = y_longhand = 0;
        t1 = t2 = 0d;
        tick();
    }

    /**
     * Randomly moves the clock's hands by a small amount in the clockwise direction
     */
    public void tick() {
        double rad1 = size / 2d;
        t1 += Math.random() * Math.PI / 8 + 1e-10;
        x_longhand = (int) Math.round(rad1 * Math.cos(t1)) + x + size / 2;
        y_longhand = (int) Math.round(rad1 * Math.sin(t1)) + y + size / 2;
        double rad2 = size / 3d;
        t2 += Math.random() * Math.PI / 8 + 1e-10;
        x_shorthand = (int) Math.round(rad2 * Math.cos(t2)) + x + size / 2;
        y_shorthand = (int) Math.round(rad2 * Math.sin(t2)) + y + size / 2;

    }

    /**
     * Draws the clock
     *
     * @param g2 the graphics object
     */
    public void draw(Graphics2D g2) {
        Stroke defaultStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.decode("#DBC7BA"));
        g2.fillOval(x, y, size, size);
        g2.setColor(Color.decode("#A19187"));
        g2.drawOval(x, y, size, size);
        g2.setColor(Color.decode("#54524E"));
        g2.drawLine(x + size / 2, y + size / 2, x_shorthand, y_shorthand);
        g2.drawLine(x + size / 2, y + size / 2, x_longhand, y_longhand);

        double rad = size * 2 / 5d;
        double inc = Math.PI / 6;
        double theta = 0;
        int num = 3;
        int offset = 3;
        for (int i = 0; i < 12; i++) {
            int x_cur = (int) (Math.round(rad * Math.cos(theta))) + x + size / 2 - offset;
            int y_cur = (int) (Math.round(rad * Math.sin(theta))) + y + size / 2 + offset;
            g2.drawString(Integer.toString(num), x_cur, y_cur);
            theta += inc;
            num = num % 12 + 1;
        }
        g2.setStroke(defaultStroke);
        g2.setColor(Color.black);
    }
}
