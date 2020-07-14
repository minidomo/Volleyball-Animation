/*
JB Ladera
4th Period
Yee
 */

import javax.swing.*;
import java.awt.*;

/**
 * A class to animate an analog clock's hands
 */
public class MovingClock extends Clock implements Animatable {
    private JComponent component;
    private int delay, ticks;

    /**
     * Creates a clock animation
     *
     * @param x         the top left x coordinate of the clock
     * @param y         the top left y coordinate of the clock
     * @param size      the width and height of the clock
     * @param ticks     the amount of times to move the hands of the clock
     * @param delay     the delay between each movement of the hands of the clock
     * @param component the JComponent of this object
     */
    public MovingClock(int x, int y, int size, int ticks, int delay, JComponent component) {
        super(x, y, size);
        this.ticks = ticks;
        this.delay = delay;
        this.component = component;
    }

    /**
     * Moves the clock's hands with a delay between movements
     *
     * @throws InterruptedException if the thread is interrupted
     */
    public void animate() throws InterruptedException {
        for (int x = 0; x < ticks; x++) {
            tick();
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
