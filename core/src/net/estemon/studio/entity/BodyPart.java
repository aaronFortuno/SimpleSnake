package net.estemon.studio.entity;

import net.estemon.studio.config.GameConfig;

public class BodyPart extends EntityBase {

    public BodyPart() {
        setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SPEED);
    }
}
