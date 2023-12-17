package net.estemon.studio.screen.game;


import com.badlogic.gdx.utils.Logger;

import net.estemon.studio.entity.SnakeHead;

public class GameController {

    // constants
    private static final Logger LOG = new Logger(GameController.class.getName());

    // attributes
    private SnakeHead head;

    // constructors
    public GameController() {
        head = new SnakeHead();
    }

    // public methods
    public void update(float delta) {

    }

    public SnakeHead getHead() {
        return head;
    }
}
