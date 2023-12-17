package net.estemon.studio.entity;

import net.estemon.studio.config.GameConfig;

public class SnakeHead extends EntityBase {

    // constructor

    public SnakeHead() {
        setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);
    }
}
