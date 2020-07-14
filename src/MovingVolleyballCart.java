/*
JB Ladera
4th Period
Yee
 */

import javax.swing.*;
import java.awt.*;

/**
 * A class to animate a volleyball cart
 */
public class MovingVolleyballCart extends VolleyballCart implements Animatable {
    private JComponent component;
    private int delay, repeat;

    /**
     * Creates a volleyball cart animation
     *
     * @param x         the initial x coordinate of the volleyball cart
     * @param y         the initial y coordinate of the volleyball cart
     * @param size      the width and height of the volleyball cart
     * @param repeat    the amount of times to "move" the volleyball cart
     * @param delay     the delay between movements of the volleyball cart
     * @param component the JComponent of this object
     */
    public MovingVolleyballCart(int x, int y, int size, int repeat, int delay, JComponent component) {
        super(x, y, size);
        this.repeat = repeat;
        this.delay = delay;
        this.component = component;
    }

    /**
     * Moves the object with delay between movements
     *
     * @throws InterruptedException if the thread is interrupted
     */
    public void animate() throws InterruptedException {
        for (int x = 0; x < repeat; x++) {
            move();
            pause();
        }
    }

    /**
     * Draws the object with the super class' draw method
     *
     * @param g2 the graphics object
     */
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    /**
     * Pauses the current thread
     *
     * @throws InterruptedException if the thread is interrupted
     */
    private void pause() throws InterruptedException {
        component.repaint();
        Thread.sleep(delay);
    }
}