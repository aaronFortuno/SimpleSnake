package net.estemon.studio.screen.game;

import com.badlogic.gdx.ScreenAdapter;

import net.estemon.studio.SimpleSnakeGame;

public class GameScreen extends ScreenAdapter {

    // attributes
    private final SimpleSnakeGame game;

    // constructors
    public GameScreen(SimpleSnakeGame game) {
        this.game = game;
    }
}
