package net.estemon.studio.screen.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Logger;

import net.estemon.studio.config.GameConfig;
import net.estemon.studio.entity.BodyPart;
import net.estemon.studio.entity.Coin;
import net.estemon.studio.entity.Direction;
import net.estemon.studio.entity.Snake;
import net.estemon.studio.entity.SnakeHead;

public class GameController {

    // attributes
    private Snake snake;
    private float timer;

    private Coin coin;

    // constructors
    public GameController() {
        snake = new Snake();
        coin = new Coin();
    }

    // public methods
    public void update(float delta) {
        queryInput();
        queryDebugInput();

        timer += delta;
        if (timer >= GameConfig.MOVE_TIME) {
            timer = 0;
            snake.move();

            checkCollision();
            checkOutOfBounds();
        }

        spawnCoin();
    }

    public Snake getSnake() {
        return snake;
    }

    public Coin getCoin() {
        return coin;
    }

    // private methods
    private void queryInput() {
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (leftPressed) {
            snake.setDirection(Direction.LEFT);
        } else if (rightPressed) {
            snake.setDirection(Direction.RIGHT);
        } else if (upPressed) {
            snake.setDirection(Direction.UP);
        } else if (downPressed) {
            snake.setDirection(Direction.DOWN);
        }
    }

    private void queryDebugInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            snake.insertBodyPart();
        }
    }

    private void checkCollision() {
        // head <-> coin collision
        SnakeHead head = snake.getHead();
        Rectangle headBounds = head.getBounds();
        Rectangle coinBounds = coin.getBounds();

        boolean overlaps = Intersector.overlaps(headBounds, coinBounds);

        if (coin.isAvailable() && overlaps) {
            snake.insertBodyPart();
            coin.setAvailable(false);
        }

        // head <-> body parts collision
        for (BodyPart bodyPart : snake.getBodyParts()) {
            Rectangle bodyPartBounds = bodyPart.getBounds();
            if (Intersector.overlaps(bodyPartBounds, headBounds)) {

            }
        }
    }

    private void checkOutOfBounds() {
        SnakeHead head = snake.getHead();

        // check x bounds
        if (head.getX() >= GameConfig.WORLD_WIDTH) {
            head.setX(0);
        } else if (head.getX() < 0) {
            head.setX(GameConfig.WORLD_WIDTH - GameConfig.SNAKE_SPEED);
        }

        // check y bounds
        if (head.getY() >= GameConfig.WORLD_HEIGHT) {
            head.setY(0);
        } else if (head.getY() < 0) {
            head.setY(GameConfig.WORLD_HEIGHT - GameConfig.SNAKE_SPEED);
        }
    }

    private void spawnCoin() {
        if (!coin.isAvailable()) {
            float coinX = MathUtils.floor(MathUtils.random((int) GameConfig.WORLD_WIDTH - GameConfig.COIN_SIZE));
            float coinY = MathUtils.floor(MathUtils.random((int) GameConfig.WORLD_HEIGHT - GameConfig.COIN_SIZE));
            coin.setAvailable(true);

            coin.setPosition(coinX, coinY);
        }
    }
}
