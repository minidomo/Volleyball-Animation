/*
JB Ladera
4th Period
Yee
 */

import javax.swing.*;
import java.awt.*;

/**
 * A class to animate a spiker
 */
public class MovingSpiker extends Spiker implements Animatable {
    private JComponent component;
    private int delay, repeat;

    /**
     * Creates a MovingSpider animation
     *
     * @param x         the initial x coordinate of when a spiker leaps
     * @param y         the initial y coordinate of when a spiker leaps
     * @param repeat    the amount of times to repeat the entire animation
     * @param component the JComponent of this object
     */
    public MovingSpiker(int x, int y, int repeat, JComponent component) {
        super(x, y);
        this.repeat = repeat;
        this.component = component;
        delay = ((int) (Math.random() * 8000) + 1000) / MAX_FRAMES;
    }

    /**
     * Randomly change the delay between frames of the animation
     */
    private void changeDelay() {
        delay = ((int) (Math.random() * 10000) + 1000) / MAX_FRAMES;
    }


    /**
     * Moves the object with a random delay between movements
     *
     * @throws InterruptedException if the thread is interrupted
     */
    public void animate() throws InterruptedException {
        for (int x = 0; x < repeat; x++) {
            for (int i = 0; i < MAX_FRAMES; i++) {
                nextFrame();
                pause();
            }
            changeDelay();
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
