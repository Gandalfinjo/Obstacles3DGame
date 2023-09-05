package com.example.obstacles3dgame.obstacles;

import com.example.obstacles3dgame.utilities.Position;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class DefaultObstacle extends ObstacleBody {
    private static final Color DEFAULT_OBSTACLE_COLOR = Color.WHITESMOKE;
    private static final PhongMaterial OBSTACLE_MATERIAL = new PhongMaterial(DEFAULT_OBSTACLE_COLOR);
    private static final double DEFAULT_OBSTACLE_DIMENSION = 28;

    private Box body;

    public DefaultObstacle(Position position) {
        super(position);
    }

    @Override
    protected void createObstacleBody() {
        body = new Box(DEFAULT_OBSTACLE_DIMENSION, DEFAULT_OBSTACLE_DIMENSION, DEFAULT_OBSTACLE_DIMENSION);
        body.setMaterial(OBSTACLE_MATERIAL);
        getChildren().add(body);
    }

    @Override
    public double getObstacleHeight() {
        return body.getHeight();
    }
}
