package com.example.obstacles3dgame.obstacles;

import com.example.obstacles3dgame.objects.GameObject;
import com.example.obstacles3dgame.utilities.Position;

public abstract class ObstacleBody extends GameObject {
    public ObstacleBody(Position position) {
        super(position);
        createObstacleBody();
    }

    protected abstract void createObstacleBody();

    public abstract double getObstacleHeight();
}
