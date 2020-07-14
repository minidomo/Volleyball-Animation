/*
JB Ladera
4th Period
Yee
 */

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A class to represent a runner that runs across the volleyball court
 */
public class Runner {
    private int x, y, frameIndex;

    public static int MAX_FRAMES = 27;

    private static final int
            HEAD_SIZE = 36,
            BODY_PART_LENGTH = 30,
            STROKE_WIDTH = 4,
            ANGLES = 10;
    private static final Stroke stroke = new BasicStroke(STROKE_WIDTH);
    private static int[][]
            FRAMES_PLAYER = new int[MAX_FRAMES][ANGLES],
            FRAME_PLAYER_OFFSET = new int[MAX_FRAMES][2];

    static {
        try {
            BufferedReader sc = new BufferedReader(new InputStreamReader(Runner.class.getResourceAsStream("resources/data/runner_frames.txt")));
            String line;
            int index = 0;
            while ((line = sc.readLine()) != null) {
                String[] parts = line.split("\\|");
                String[] strFrameOffset = parts[0].split(",");
                FRAME_PLAYER_OFFSET[index][0] = Integer.parseInt(strFrameOffset[0]);
                FRAME_PLAYER_OFFSET[index][1] = Integer.parseInt(strFrameOffset[1]);
                String[] strAngles = parts[1].split(",");
                int[] bearingAngles = new int[ANGLES];
                for (int x = 0; x < ANGLES; x++)
                    bearingAngles[x] = Integer.parseInt(strAngles[x]);
                FRAMES_PLAYER[index] = bearingAngles;
                index++;
            }
            sc.close();
            if (index != MAX_FRAMES)
                throw new Exception("less frames than expected: " + index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a runner at a specified coordinate
     *
     * @param x the x coordinate of the initial location of the runner
     * @param y the y coordinate of the initial location of the runner
     */
    public Runner(int x, int y) {
        this.x = x;
        this.y = y;
        frameIndex = 0;
    }

    /**
     * Allows the {@link #draw(Graphics2D)} to draw the next frame
     */
    public void nextFrame() {
        frameIndex = (frameIndex + 1) % MAX_FRAMES;
    }

    /**
     * Called by {@link #draw(Graphics2D)} to draw the next frame of the runner
     *
     * @param g2 the graphics object
     */
    private void drawFrame(Graphics2D g2) {
        int[] bearingAngles = FRAMES_PLAYER[frameIndex];
        Point chestStart = new Point(x + FRAME_PLAYER_OFFSET[frameIndex][0], y - FRAME_PLAYER_OFFSET[frameIndex][1]);
        Point chestEnd = Utility.getEndpoint(chestStart, bearingAngles[0], BODY_PART_LENGTH);
        Point stomachEnd = Utility.getEndpoint(chestEnd, bearingAngles[1], BODY_PART_LENGTH);
        Point leftThighEnd = Utility.getEndpoint(stomachEnd, bearingAngles[2], BODY_PART_LENGTH);
        Point leftCalfEnd = Utility.getEndpoint(leftThighEnd, bearingAngles[3], BODY_PART_LENGTH);
        Point rightThighEnd = Utility.getEndpoint(stomachEnd, bearingAngles[4], BODY_PART_LENGTH);
        Point rightCalfEnd = Utility.getEndpoint(rightThighEnd, bearingAngles[5], BODY_PART_LENGTH);
        Point leftBicepEnd = Utility.getEndpoint(chestStart, bearingAngles[6], BODY_PART_LENGTH);
        Point leftForearmEnd = Utility.getEndpoint(leftBicepEnd, bearingAngles[7], BODY_PART_LENGTH);
        Point rightBicepEnd = Utility.getEndpoint(chestStart, bearingAngles[8], BODY_PART_LENGTH);
        Point rightForearmEnd = Utility.getEndpoint(rightBicepEnd, bearingAngles[9], BODY_PART_LENGTH);
        Utility.drawLine(g2, chestStart, chestEnd);
        Utility.drawLine(g2, chestEnd, stomachEnd);
        Utility.drawLine(g2, stomachEnd, leftThighEnd);
        Utility.drawLine(g2, leftThighEnd, leftCalfEnd);
        Utility.drawLine(g2, stomachEnd, rightThighEnd);
        Utility.drawLine(g2, rightThighEnd, rightCalfEnd);
        Utility.drawLine(g2, chestStart, leftBicepEnd);
        Utility.drawLine(g2, leftBicepEnd, leftForearmEnd);
        Utility.drawLine(g2, chestStart, rightBicepEnd);
        Utility.drawLine(g2, rightBicepEnd, rightForearmEnd);
        drawHead(g2, chestStart, chestEnd);
        int floorLevel = y + 100;
        g2.setColor(new Color(0, 0, 0, 20));
        g2.fillOval(chestEnd.x - HEAD_SIZE, floorLevel, HEAD_SIZE * 2, 10);
        g2.setColor(Color.BLACK);
    }

    /**
     * Draws the runner
     *
     * @param g2 the graphics spiker
     */
    public void draw(Graphics2D g2) {
        Stroke defaultStroke = g2.getStroke();
        g2.setStroke(stroke);
        drawFrame(g2);
        g2.setStroke(defaultStroke);
    }


    /**
     * Draws the head perpendicular to the spiker's chest
     *
     * @param g2         the graphics object
     * @param chestStart the coordinate of the spiker's upper chest
     * @param chestEnd   the coordinate of the spiker's lower chest
     */
    private void drawHead(Graphics2D g2, Point chestStart, Point chestEnd) {
        double angleRad = Math.atan2(chestStart.y - chestEnd.y, chestStart.x - chestEnd.x);
        double headx = HEAD_SIZE / 2d * Math.cos(angleRad);
        double heady = HEAD_SIZE / 2d * -Math.sin(angleRad);
        g2.drawOval((int) Math.round(chestStart.x + headx - HEAD_SIZE / 2d), (int) Math.round(chestStart.y - heady - HEAD_SIZE / 2d), HEAD_SIZE, HEAD_SIZE);
    }
}
