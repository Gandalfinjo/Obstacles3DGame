package com.example.obstacles3dgame;

import com.example.obstacles3dgame.objects.Obstacle;
import com.example.obstacles3dgame.objects.Player;
import com.example.obstacles3dgame.objects.Track;
import com.example.obstacles3dgame.utilities.Position;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Game extends Application {
    private static final double WINDOW_WIDTH = 720;
    private static final double WINDOW_HEIGHT = 400;

    private static final double OBSTACLE_SPAWN_DEPTH = 1200;

    private static final Color DEFAULT_BACKGROUND_COLOR = Color.CADETBLUE;

    private static final int DEFAULT_OBSTACLE_TARGET_COUNT = 5;
    private static final long DEFAULT_OBSTACLE_CREATION_SPEED = 1500000000;

    private long lastObstacleCreatedTime = 0;
    private int obstacleCount = 0;

    private static boolean isGameActive = true;

    private final UpdateTimer timer = new UpdateTimer();

    private Group objects;
    private Player player;
    private Track track;

    private class UpdateTimer extends AnimationTimer {
        @Override
        public void handle(long now) {
            updateObstacles(now);
        }
    }

    private void updateObstacles(long now) {
        List<Node> children = objects.getChildren();

        for (int i = 0; i < objects.getChildren().size(); i++) {
            Node child = children.get(i);

            if (child instanceof Obstacle) {
                if (child.getBoundsInParent().intersects((player.localToScene(player.getParentBounds())))) {
                    isGameActive = false;
                    return;
                }

                if (obstacleCount > 0 && !((Obstacle)child).move()) {
                    obstacleCount--;
                    objects.getChildren().remove(child);
                }
            }
        }

        if (obstacleCount < DEFAULT_OBSTACLE_TARGET_COUNT && now > lastObstacleCreatedTime + DEFAULT_OBSTACLE_CREATION_SPEED) {
            lastObstacleCreatedTime = now;
            objects.getChildren().add(new Obstacle(new Position(track.getRandomX(), track.getY(), OBSTACLE_SPAWN_DEPTH)));
            obstacleCount++;
        }
    }

    public static boolean isGameActive() {
        return isGameActive;
    }

    @Override
    public void start(Stage stage) {
        objects = new Group();
        player = Player.instantiatePlayer();
        track = new Track();

        AmbientLight ambientLight = new AmbientLight(Color.WHITE);
        ambientLight.setOpacity(0.2);
        ambientLight.setTranslateZ(-1000);
        ambientLight.setBlendMode(BlendMode.SOFT_LIGHT);

        Scene scene = new Scene(objects, WINDOW_WIDTH, WINDOW_HEIGHT, true, SceneAntialiasing.BALANCED);
        scene.setFill(DEFAULT_BACKGROUND_COLOR);
        scene.setCursor(Cursor.NONE);
        scene.setCamera(player.getCamera());
        scene.setOnMouseMoved(player);
        scene.setOnKeyPressed(player);
        scene.setOnKeyReleased(player);

        objects.getChildren().addAll(player, track, ambientLight);

        stage.setTitle("Obstacles");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();

        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}