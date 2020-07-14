/*
JB Ladera
4th Period
Yee
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

/**
 * Starter class to run the program
 */
public class Viewer {
    public static final int WIDTH = 800, HEIGHT = 600;

    /**
     * Runs the GUI
     *
     * @param args unused
     * @throws IOException if no icon image is found
     */
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Volleyball");
        frame.setIconImage(ImageIO.read(Viewer.class.getResource("resources/images/volleyball.png")));
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // center jframe on screen
        VolleyballSceneComponent scene = new VolleyballSceneComponent();
        frame.add(scene);
        frame.setVisible(true);
        scene.startAnimation();
    }
}
