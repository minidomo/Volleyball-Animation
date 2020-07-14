/*
JB Ladera
4th Period
Yee
 */

/**
 * An interface for objects that are to be animated
 */
public interface Animatable {
    /**
     * Moves the object with a delay between movements
     *
     * @throws InterruptedException if the thread is interrupted
     */
    void animate() throws InterruptedException;
}