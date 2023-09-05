package com.example.obstacles3dgame.objects;

import com.example.obstacles3dgame.utilities.Position;
import javafx.scene.Group;

public class GameObject extends Group {
    protected Position position;

    public GameObject(Position position) {
        this.position = position;
    }
}
