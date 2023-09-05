package com.example.obstacles3dgame.objects;

import com.example.obstacles3dgame.Game;
import com.example.obstacles3dgame.utilities.Position;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Box;

public class Player extends GameObject implements EventHandler<Event> {
    private static final double DEFAULT_POSITION_X = 0;
    private static final double DEFAULT_POSITION_Y = 0;
    private static final double DEFAULT_POSITION_Z = 0;

    public static final double NEAR_CLIP = 0.1;
    public static final double FAR_CLIP = 10000;
    public static final double FIELD_OF_VIEW = 60;

    private final PerspectiveCamera camera;
    private final Box shape;

    private int lane = 1;

    public Player(Position position) {
        super(position);

        shape = new Box(30.0, 30.0, 30.0);
        shape.setVisible(false);

        camera = new PerspectiveCamera(true);
        camera.setNearClip(NEAR_CLIP);
        camera.setFarClip(FAR_CLIP);
        camera.setFieldOfView(FIELD_OF_VIEW);

        setTranslateY(position.getY());

        getChildren().addAll(shape, camera);
    }

    public static Player instantiatePlayer() {
        return new Player(new Position(DEFAULT_POSITION_X, DEFAULT_POSITION_Y, DEFAULT_POSITION_Z));
    }

    @Override
    public void handle(Event event) {
        if (event instanceof KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.ESCAPE && keyEvent.getEventType() == KeyEvent.KEY_PRESSED)
                System.exit(0);
            else {
                if (!Game.isGameActive()) return;

                if ((keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.LEFT) && keyEvent.getEventType() == KeyEvent.KEY_PRESSED)
                    moveLeft();
                else if ((keyEvent.getCode() == KeyCode.D || keyEvent.getCode() == KeyCode.RIGHT) && keyEvent.getEventType() == KeyEvent.KEY_PRESSED)
                    moveRight();
            }
        }
    }

    public Bounds getParentBounds() {
        return shape.getBoundsInParent();
    }

    public Camera getCamera() {
        return camera;
    }

    private void moveLeft() {
        if (lane == 0) return;

        lane--;
        setTranslateX(getTranslateX() - Track.LANE_WIDTH);
    }

    private void moveRight() {
        if (lane == 2) return;

        lane++;
        setTranslateX(getTranslateX() + Track.LANE_WIDTH);
    }
}
