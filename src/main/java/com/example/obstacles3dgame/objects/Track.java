package com.example.obstacles3dgame.objects;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.Random;

public class Track extends Group {
    public static final double LANE_HEIGHT = 0.5;
    public static final double LANE_WIDTH = 30;
    public static final double LANE_LENGTH = 10000;

    public static final double DEFAULT_TRANSLATE_Y = 30;
    public static final double DEFAULT_DISTANCE_BETWEEN_LANES = 2;

    private static final Color DEFAULT_TRACK_COLOR = Color.CORAL;
    private static final PhongMaterial TRACK_MATERIAL = new PhongMaterial(DEFAULT_TRACK_COLOR);

    private static final Color DEFAULT_LINE_COLOR = Color.WHITE;
    private static final PhongMaterial LINE_MATERIAL = new PhongMaterial(DEFAULT_LINE_COLOR);

    private static final Color DEFAULT_GRASS_COLOR = Color.GREEN;
    private static final PhongMaterial GRASS_MATERIAL = new PhongMaterial(DEFAULT_GRASS_COLOR);

    private final Box[] lanes;

    public Track() {
        lanes = new Box[3];
        for (int i = 0; i < lanes.length; i++)
            lanes[i] = createLane(i);

        Box lines = new Box(LANE_WIDTH * 2, LANE_HEIGHT, LANE_LENGTH);
        lines.setMaterial(LINE_MATERIAL);
        lines.setTranslateX(lanes[1].getTranslateX());
        lines.setTranslateY(DEFAULT_TRANSLATE_Y + 1);

        Box grass = new Box(LANE_WIDTH * 8 , LANE_HEIGHT, LANE_LENGTH);
        grass.setMaterial(GRASS_MATERIAL);
        grass.setTranslateX(lanes[1].getTranslateX());
        grass.setTranslateY(DEFAULT_TRANSLATE_Y + 2);

        getChildren().addAll(lanes);
        getChildren().addAll(lines, grass);
    }

    public Box createLane(int i) {
        Box lane = new Box(LANE_WIDTH, LANE_HEIGHT, LANE_LENGTH);
        lane.setMaterial(TRACK_MATERIAL);
        lane.setTranslateX((-1 + i) * (LANE_WIDTH + DEFAULT_DISTANCE_BETWEEN_LANES));
        lane.setTranslateY(DEFAULT_TRANSLATE_Y);
        return lane;
    }

    public double getY() {
        return lanes[0].getTranslateY();
    }

    public double getRandomX() {
        return lanes[new Random().nextInt(3)].getTranslateX();
    }
}
