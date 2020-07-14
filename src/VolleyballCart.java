/*
JB Ladera
4th Period
Yee
 */

import java.awt.*;

/**
 * A class to represent a volleyball cart that holds volleyballs
 */
public class VolleyballCart {
    private int x, y, size, volleyballSize, x_left;
    private Point[] cartPoints;
    private int[][] volleyballOffsets;
    private int delta;

    /**
     * Creates a volleyball cart at a specified location and size
     *
     * @param x    the top left x coordinate of the cart
     * @param y    the top left y coordinate of the cart
     * @param size the width and height of the cart
     */
    public VolleyballCart(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        volleyballSize = (int) Math.round(size / 2d);
        x_left = x;
        if (volleyballSize % 2 == 1)
            volleyballSize++;
        delta = Math.random() > .5 ? 10 : -10;
        cartPoints = new Point[8];
        volleyballOffsets = new int[6][2];
        assignPoints();
        assignOffsets();
    }

    /**
     * Randomly assigns offset values relative to the cart's x and y positions to position volleyballs inside the cart
     */
    private void assignOffsets() {
        for (int i = 0; i < volleyballOffsets.length; i++) {
            volleyballOffsets[i][0] = (int) (Math.random() * (cartPoints[7].x - volleyballSize - cartPoints[0].x + 1));
            volleyballOffsets[i][1] = (int) (Math.random() * (cartPoints[4].y - volleyballSize / 2 - cartPoints[0].y + 1));
        }
    }

    /**
     * Updates the cart's points to represent the location of the cart's 8 corners
     */
    private void assignPoints() {
        cartPoints[0] = new Point(x, y);
        cartPoints[1] = new Point(x + size, y);
        cartPoints[2] = new Point(x, y + size);
        cartPoints[3] = new Point(x + size, y + size);
        cartPoints[4] = new Point(x + size / 2, y + size / 2);
        cartPoints[5] = new Point(cartPoints[4].x + size, cartPoints[4].y);
        cartPoints[6] = new Point(cartPoints[4].x, cartPoints[4].y + size);
        cartPoints[7] = new Point(cartPoints[4].x + size, cartPoints[4].y + size);
    }

    /**
     * Updates the x coordinate of the cart and obtains the new points for the cart's 8 corners
     */
    public void move() {
        if (x + size >= Viewer.WIDTH || x < x_left)
            delta = -delta;
        x += delta;
        assignPoints();
    }

    /**
     * Draws the volleyball cart with volleyballs in it
     *
     * @param g2 the graphics object
     */
    public void draw(Graphics2D g2) {
        Stroke defaultStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(3));

        int wheelSize = (int) Math.round(size / 10d);
        int len = (int) Math.round(size / 2d);
        Point p1 = new Point(cartPoints[2].x, cartPoints[2].y + len);
        Point p2 = new Point(cartPoints[6].x, cartPoints[6].y + len);
        Point p3 = new Point(cartPoints[3].x, cartPoints[3].y + len);
        Point p4 = new Point(cartPoints[7].x, cartPoints[7].y + len);

        g2.setColor(new Color(0, 0, 0, 20));
        g2.fillOval(x - size / 3, p1.y - 15, (int) Math.round(size * 7 / 3d), size);

        Color steel = Color.decode("#8C8479");
        Color wheel = Color.decode("#4B433F");
        g2.setColor(steel);
        Utility.drawLine(g2, cartPoints[2], p1);
        Utility.drawLine(g2, cartPoints[6], p2);
        Utility.drawLine(g2, cartPoints[3], p3);
        Utility.drawLine(g2, cartPoints[7], p4);
        g2.setStroke(new BasicStroke(2));
        g2.fillOval(p1.x - wheelSize / 2, p1.y, wheelSize, wheelSize);
        g2.setColor(wheel);
        g2.drawOval(p1.x - wheelSize / 2, p1.y, wheelSize, wheelSize);
        g2.setColor(steel);
        g2.fillOval(p2.x - wheelSize / 2, p2.y, wheelSize, wheelSize);
        g2.setColor(wheel);
        g2.drawOval(p2.x - wheelSize / 2, p2.y, wheelSize, wheelSize);
        g2.setColor(steel);
        g2.fillOval(p3.x - wheelSize / 2, p3.y, wheelSize, wheelSize);
        g2.setColor(wheel);
        g2.drawOval(p3.x - wheelSize / 2, p3.y, wheelSize, wheelSize);
        g2.setColor(steel);
        g2.fillOval(p4.x - wheelSize / 2, p4.y, wheelSize, wheelSize);
        g2.setColor(wheel);
        g2.drawOval(p4.x - wheelSize / 2, p4.y, wheelSize, wheelSize);

        g2.setStroke(new BasicStroke(3));
        Color side = Color.decode("#3F61A9");
        Color border = Color.decode("#284375");
        g2.setColor(side);
        Polygon leftSide = new Polygon();
        leftSide.addPoint(cartPoints[0].x, cartPoints[0].y);
        leftSide.addPoint(cartPoints[2].x, cartPoints[2].y);
        leftSide.addPoint(cartPoints[6].x, cartPoints[6].y);
        leftSide.addPoint(cartPoints[4].x, cartPoints[4].y);
        Polygon rightSide = new Polygon();
        rightSide.addPoint(cartPoints[1].x, cartPoints[1].y);
        rightSide.addPoint(cartPoints[3].x, cartPoints[3].y);
        rightSide.addPoint(cartPoints[7].x, cartPoints[7].y);
        rightSide.addPoint(cartPoints[5].x, cartPoints[5].y);
        g2.fillRect(cartPoints[0].x, cartPoints[0].y, size, size);
        g2.fillPolygon(rightSide);

        g2.setColor(border);
        Utility.drawLine(g2, cartPoints[0], cartPoints[1]);
        Utility.drawLine(g2, cartPoints[1], cartPoints[5]);
        Utility.drawLine(g2, cartPoints[1], cartPoints[3]);
        for (int[] circleOffset : volleyballOffsets) {
            g2.setColor(Color.decode("#CEA850"));
            g2.fillOval(cartPoints[0].x + circleOffset[0], cartPoints[0].y + circleOffset[1], volleyballSize, volleyballSize);
            g2.setColor(Color.decode("#484878"));
            g2.drawOval(cartPoints[0].x + circleOffset[0], cartPoints[0].y + circleOffset[1], volleyballSize, volleyballSize);
        }
        g2.setColor(side);
        g2.fillRect(cartPoints[4].x, cartPoints[4].y, size, size);
        g2.fillPolygon(leftSide);
        g2.setColor(border);
        Utility.drawLine(g2, cartPoints[0], cartPoints[2]);
        Utility.drawLine(g2, cartPoints[4], cartPoints[5]);
        Utility.drawLine(g2, cartPoints[4], cartPoints[6]);
        Utility.drawLine(g2, cartPoints[5], cartPoints[7]);
        Utility.drawLine(g2, cartPoints[6], cartPoints[7]);
        Utility.drawLine(g2, cartPoints[0], cartPoints[4]);
        Utility.drawLine(g2, cartPoints[2], cartPoints[6]);

        g2.setStroke(defaultStroke);
        g2.setColor(Color.black);
    }
}
