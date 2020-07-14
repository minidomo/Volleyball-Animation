/*
JB Ladera
4th Period
Yee
 */

import java.awt.*;

/**
 * A class that provides methods to draw lines and obtain points based on angles in Bearing
 */
public class Utility {

    /**
     * Prevent instantiate of this class
     */
    private Utility() {
    }

    /**
     * Returns the endpoint from a specified starting point, angle in bearing, and length
     *
     * @param start        the starting point
     * @param bearingAngle the angle in bearing
     * @param distance     the distance away from the starting point
     * @return the endpoint from a specified starting point, angle in bearing, and length
     */
    public static Point getEndpoint(Point start, double bearingAngle, double distance) {
        return getEndpoint(start.x, start.y, bearingAngle, distance);
    }

    /**
     * Returns the endpoint from a specified starting x and y coordinate, angle in bearing, and length
     *
     * @param x            the starting x coordinate
     * @param y            the starting y coordinate
     * @param bearingAngle the angle in bearing
     * @param distance     the distance away from the starting point
     * @return the endpoint from a specified starting x and y coordinate, angle in bearing, and length
     */
    public static Point getEndpoint(int x, int y, double bearingAngle, double distance) {
        double stdAngle = (bearingAngle + 270) % 360;
        double rad = Math.toRadians(stdAngle);
        int xe = (int) (Math.round(distance * Math.cos(rad))) + x;
        int ye = (int) (Math.round(distance * Math.sin(rad))) + y;
        return new Point(xe, ye);
    }

    /**
     * Draws a line from a specified starting point, angle in bearing, and length
     *
     * @param g2           the graphics object
     * @param start        the starting point
     * @param bearingAngle the angle in bearing
     * @param length       the length of the line
     */
    public static void drawLine(Graphics2D g2, Point start, double bearingAngle, double length) {
        drawLine(g2, start.x, start.y, bearingAngle, length);
    }

    /**
     * Draws a line from a specified starting x and y coordinate, angle in bearing, and length
     *
     * @param g2           the graphics object
     * @param x            the starting x coordinate
     * @param y            the starting y coordinate
     * @param bearingAngle the angle in bearing
     * @param length       the length of the line
     */
    public static void drawLine(Graphics2D g2, int x, int y, double bearingAngle, double length) {
        Point p = getEndpoint(x, y, bearingAngle, length);
        g2.drawLine(x, y, p.x, p.y);
    }

    /**
     * Draws a line from a specified starting point and ending point
     *
     * @param g2    the graphics object
     * @param start the starting point
     * @param end   the ending point
     */
    public static void drawLine(Graphics2D g2, Point start, Point end) {
        g2.drawLine(start.x, start.y, end.x, end.y);
    }
}
