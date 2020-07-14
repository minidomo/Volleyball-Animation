/*
JB Ladera
4th Period
Yee
 */

import java.awt.*;

/**
 * Class to draw the background
 */
public class VolleyballCourt {
    private static final int
            NET_HORIZONTAL_LINES = 7,
            NET_VERTICAL_LINES = 10,
            POLE_HEIGHT = 215,
            POLE_WIDTH = 10;

    /**
     * Draws the background
     *
     * @param g2 the graphics object
     */
    public void draw(Graphics2D g2) {
        int x1 = 210, x2 = 460;
        int y1 = 50, y2 = 300;
        int wallYEnd = 175;
        drawWall(g2, 0, 0, Viewer.WIDTH, wallYEnd);
        drawFloor(g2, 0, wallYEnd, Viewer.WIDTH, Viewer.HEIGHT - wallYEnd);
        drawWallFloorSeparation(g2, 0, wallYEnd, Viewer.WIDTH, 3);
        drawGroundMarks(g2, y1 + POLE_HEIGHT, y2 + POLE_HEIGHT, x1, x2, 3);
        drawNet(g2, x1 - 10, y1 - 1, x2 + 10, y2 + 10, 3);
        drawPole(g2, x1 - 10, y1 - 10);
        drawPole(g2, x2 + 10, y2 + 10);
    }

    /**
     * Draws the line between the wall and the floor at a specified location
     *
     * @param g2 the graphics object
     * @param x  the top left x coordinate of the rectangle
     * @param y  the top left y coordinate of the rectangle
     * @param w  the width of the rectangle
     * @param h  the height of the rectangle
     */
    private void drawWallFloorSeparation(Graphics2D g2, int x, int y, int w, int h) {
        g2.setColor(Color.decode("#7E5A3B"));
        g2.fillRect(x, y, w, h);
        g2.setColor(Color.black);
    }

    /**
     * Colors the floor at a specified location
     *
     * @param g2 the graphics object
     * @param x  the top left x coordinate of the rectangle
     * @param y  the top left y coordinate of the rectangle
     * @param w  the width of the rectangle
     * @param h  the height of the rectangle
     */
    private void drawFloor(Graphics2D g2, int x, int y, int w, int h) {
        g2.setColor(Color.decode("#CA9F5A"));
        g2.fillRect(x, y, w, h);
        g2.setColor(Color.black);
    }

    /**
     * Colors the wall at a specified location
     *
     * @param g2 the graphics object
     * @param x  the top left x coordinate of the rectangle
     * @param y  the top left y coordinate of the rectangle
     * @param w  the width of the rectangle
     * @param h  the height of the rectangle
     */
    private void drawWall(Graphics g2, int x, int y, int w, int h) {
        Color[] colors = {Color.decode("#AA6D3C"), Color.decode("#8B5B34")};
        int width = 150;
        int curx = x;
        int color = 0;
        while (curx < w) {
            g2.setColor(Color.decode("#774724"));
            g2.drawRect(curx, y, width / 2, h);
            g2.drawRect(curx + width / 2, y, width / 2, h);
            g2.setColor(colors[color % 2]);
            g2.fillRect(curx, y, width, h);
            curx += width;
            color++;
        }
        curx = x;
        g2.setColor(Color.decode("#774724"));
        while (curx < w) {
            g2.drawRect(curx, y, width / 2, h);
            curx += width / 2;
        }
        g2.setColor(Color.black);
    }

    /**
     * Draws the volleyball marks on the floor at a specified location
     *
     * @param g2    the graphics object
     * @param y1    the bottom y coordinate of the top volleyball net pole
     * @param y2    the bottom y coordinate of the bottom volleyball net pole
     * @param x1    the x coordinate of the top volleyball net pole
     * @param x2    the x coordinate of the bottom volleyball net pole
     * @param width the width of the pole
     */
    private void drawGroundMarks(Graphics2D g2, int y1, int y2, int x1, int x2, int width) {
        Stroke defaultStroke = g2.getStroke();
        g2.setColor(Color.decode("#DCD1BB"));
        g2.fillRect(0, y1, Viewer.WIDTH, width);
        g2.fillRect(0, y2, Viewer.WIDTH, width);
        g2.setStroke(new BasicStroke(width));
        int separation = 195;
        g2.drawLine(x1 + 5, y1, x2, y2);
        g2.drawLine(x1 - separation, y1, x2 - separation, y2);
        g2.drawLine(x1 + separation, y1, x2 + separation, y2);
        g2.setStroke(defaultStroke);
        g2.setColor(Color.black);
    }

    /**
     * Draws a pole at a specified location
     *
     * @param g2 the graphics object
     * @param x  the top left x coordinate of the rectangle
     * @param y  the top left y coordinate of the rectangle
     */
    private void drawPole(Graphics2D g2, int x, int y) {
        g2.setColor(Color.decode("#A54339"));
        g2.fillRect(x, y, VolleyballCourt.POLE_WIDTH, VolleyballCourt.POLE_HEIGHT);
        g2.setColor(Color.decode("#924630"));
        g2.drawRect(x, y, VolleyballCourt.POLE_WIDTH, VolleyballCourt.POLE_HEIGHT);
        g2.setColor(Color.black);
    }

    /**
     * Draws the next between two poles
     *
     * @param g2    the graphics object
     * @param x1    the x coordinate of the top volleyball net pole
     * @param y1    the top y coordinate of the top volleyball net pole
     * @param x2    the x coordinate of the bottom volleyball net pole
     * @param y2    the top y coordinate of the bottom volleyball net pole
     * @param width the width of the net lines
     */
    private void drawNet(Graphics2D g2, int x1, int y1, int x2, int y2, int width) {
        Stroke defaultStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(width));
        g2.setColor(Color.decode("#393932"));
        x1 += 5;
        y1 += 2;
        int offsetY = 20;
        int offsetX = (int) Math.round((double) (x2 - x1) / (NET_VERTICAL_LINES + 1));
        double slope = (double) (y2 - y1) / (x2 - x1);
        int height = offsetY * (NET_HORIZONTAL_LINES - 1);
        for (int a = 1; a <= NET_VERTICAL_LINES; a++) {
            int x = x1 + offsetX * a;
            int y = (int) Math.round(slope * (x - x1) + y1);
            g2.drawLine(x, y, x, y + height);
        }
        for (int a = 0; a < NET_HORIZONTAL_LINES; a++) {
            if (a == 0 || a == NET_HORIZONTAL_LINES - 1)
                g2.setColor(Color.decode("#D9CFBF"));
            else
                g2.setColor(Color.decode("#393932"));
            g2.drawLine(x1, y1 + offsetY * a, x2, y2 + offsetY * a);
        }
        g2.setStroke(defaultStroke);
        g2.setColor(Color.black);
    }
}
