package net.estemon.studio.entity;

import com.badlogic.gdx.math.Rectangle;

public abstract class EntityBase {

    // attributes
    protected float x;
    protected float y;

    protected float width = 1f;
    protected float height = 1f;

    protected Rectangle bounds;

    // constructors

    public EntityBase() {
        bounds = new Rectangle(x, y, width, height);
    }

    // public methods
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateBounds();
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        updateBounds();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    // private methods
    private void updateBounds() {
        bounds.setPosition(x, y);
        bounds.setSize(width, height);
    }
}
