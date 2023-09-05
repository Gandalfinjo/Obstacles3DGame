package com.example.obstacles3dgame.objects;

import com.example.obstacles3dgame.Game;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.Objects;
import java.util.Random;

public class Track extends Group {
    public static final double LANE_HEIGHT = 0.5;
    public static final double LANE_WIDTH = 30;
    public static final double LANE_LENGTH = 10000;

    public static final double DEFAULT_TRANSLATE_Y = 30;
    public static final double DEFAULT_DISTANCE_BETWEEN_LANES = 2;

    private static final Color DEFAULT_LINE_COLOR = Color.WHITE;
    private static final PhongMaterial LINE_MATERIAL = new PhongMaterial(DEFAULT_LINE_COLOR);

    private final Box[] lanes;

    public Track() {
        Image grassImage = new Image(Objects.requireNonNull(Game.class.getResourceAsStream("grass.jpg")));
        PhongMaterial grassMaterial = new PhongMaterial();
        grassMaterial.setDiffuseMap(grassImage);

        lanes = new Box[3];
        for (int i = 0; i < lanes.length; i++)
            lanes[i] = createLane(i);

        Box lines = new Box(LANE_WIDTH * 2, LANE_HEIGHT, LANE_LENGTH);
        lines.setMaterial(LINE_MATERIAL);
        lines.setTranslateX(lanes[1].getTranslateX());
        lines.setTranslateY(DEFAULT_TRANSLATE_Y + 1);

        Box grass = new Box(LANE_WIDTH * 8 , LANE_HEIGHT, LANE_LENGTH);
        grass.setMaterial(grassMaterial);
        grass.setTranslateX(lanes[1].getTranslateX());
        grass.setTranslateY(DEFAULT_TRANSLATE_Y + 2);

        getChildren().addAll(lanes);
        getChildren().addAll(lines, grass);
    }

    public Box createLane(int i) {
        Image trackImage = new Image(Objects.requireNonNull(Game.class.getResourceAsStream("tartan.jpg")));
        PhongMaterial trackMaterial = new PhongMaterial();
        trackMaterial.setDiffuseMap(trackImage);

        Box lane = new Box(LANE_WIDTH, LANE_HEIGHT, LANE_LENGTH);
        lane.setMaterial(trackMaterial);
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
