package com.example.obstacles3dgame.objects;

import com.example.obstacles3dgame.obstacles.DefaultObstacle;
import com.example.obstacles3dgame.obstacles.ObstacleBody;
import com.example.obstacles3dgame.utilities.Position;

public class Obstacle extends GameObject {
    private static final double OBSTACLE_SPEED = 4;
    private final ObstacleBody obstacleBody;

    public Obstacle(Position position) {
        super(position);

        obstacleBody = new DefaultObstacle(position);

        setTranslateX(position.getX());
        setTranslateY(position.getY() - obstacleBody.getObstacleHeight() / 2);
        setTranslateZ(position.getZ());

        getChildren().add(obstacleBody);
    }

    public boolean move() {
        setTranslateZ(getTranslateZ() - OBSTACLE_SPEED);
        return isOnTrack();
    }

    public boolean isOnTrack() {
        return getTranslateZ() > 0;
    }
}
