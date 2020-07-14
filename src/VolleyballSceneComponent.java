/*
JB Ladera
4th Period
Yee
 */

import javax.swing.*;
import java.awt.*;

/**
 * The volleyball scene
 */
public class VolleyballSceneComponent extends JComponent {
    private VolleyballCourt court;
    private MovingSpiker spiker;
    private MovingClock clock;
    private MovingVolleyballCart cart;
    private MovingRunner runner;

    /**
     * Creates the different objects for the animation
     */
    public VolleyballSceneComponent() {
        court = new VolleyballCourt();
        spiker = new MovingSpiker(540, 300, 50, this);
        clock = new MovingClock(500, 40, 60, 10000, 500, this);
        cart = new MovingVolleyballCart(540, 100, 70, 500, 500, this);
        runner = new MovingRunner(-100, 450, 50, this);
    }

    /**
     * Paints the components
     *
     * @param g the graphics object
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Consolas", Font.PLAIN, 10));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        court.draw(g2);
        spiker.draw(g2);
        clock.draw(g2);
        cart.draw(g2);
        runner.draw(g2);
    }

    /**
     * Starts the animation
     */
    public void startAnimation() {
        class AnimationRunnable<T extends Animatable> implements Runnable {
            private T obj;

            public AnimationRunnable(T obj) {
                this.obj = obj;
            }

            public void run() {
                try {
                    obj.animate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        new Thread(new AnimationRunnable<>(spiker)).start();
        new Thread(new AnimationRunnable<>(clock)).start();
        new Thread(new AnimationRunnable<>(cart)).start();
        new Thread(new AnimationRunnable<>(runner)).start();
    }
}
