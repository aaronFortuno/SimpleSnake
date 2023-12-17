package net.estemon.studio.screen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.estemon.studio.config.GameConfig;
import net.estemon.studio.entity.BodyPart;
import net.estemon.studio.entity.Coin;
import net.estemon.studio.entity.Snake;
import net.estemon.studio.entity.SnakeHead;
import net.estemon.studio.utils.ViewportUtils;
import net.estemon.studio.utils.debug.DebugCameraController;
import net.estemon.studio.utils.GdxUtils;

public class GameRenderer implements Disposable {

    // attributes
    private final GameController controller;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private DebugCameraController debugCameraController;

    // constructors
    public GameRenderer(GameController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    // public methods
    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        GdxUtils.clearScreen();

        renderDebug();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        ViewportUtils.debugPixelPerUnit(viewport);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    // private methods
    private void renderDebug() {
        ViewportUtils.drawGrid(viewport, renderer);

        viewport.apply();

        Color oldColor = new Color(renderer.getColor());
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        drawDebug();

        renderer.end();
        renderer.setColor(oldColor);
    }

    private void drawDebug() {
        renderer.setColor(Color.ORANGE);

        Snake snake = controller.getSnake();

        SnakeHead head = snake.getHead();
        Rectangle headBounds = head.getBounds();
        renderer.rect(headBounds.x, headBounds.y, headBounds.width, headBounds.height);

        for (BodyPart bodyPart : snake.getBodyParts()) {
            Rectangle bodyPartBounds = bodyPart.getBounds();
            renderer.rect(bodyPartBounds.x, bodyPartBounds.y, bodyPartBounds.width, bodyPartBounds.height);
        }

        renderer.setColor(Color.GRAY);
        Coin coin = controller.getCoin();
        Rectangle coinBounds = coin.getBounds();
        renderer.rect(coinBounds.x, coinBounds.y, coinBounds.width, coinBounds.height);
    }
}
