package com.cvte.game.flappybird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.cvte.game.flappybird.collision.CollisionAble;
import com.cvte.game.flappybird.collision.CollisionCtrl;
import com.cvte.game.flappybird.com.WorldObj;

/**
 * Created by cvtpc on 2014/7/20.
 */
public class Conduit implements WorldObj, CollisionAble {

    private static final float WIDTH = 100;

    private TextureRegion conduitUp;
    private TextureRegion conduitDown;

    private Rectangle rectangleUp;
    private Rectangle rectangleDown;

    private float x;
    private float y;

    private boolean isDead;

    public Conduit(TextureAtlas atlas, float x, float y) {
        conduitUp = atlas.findRegion("conduit/conduitB1");
        conduitDown = atlas.findRegion("conduit/conduitB2");

        rectangleUp = new Rectangle();
        rectangleDown = new Rectangle();

        setX(x);
        setY(y);

        setDead(false);
    }

    @Override
    public void update(float delta) {
        checkDead();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(conduitUp, x, y + WIDTH);
        batch.draw(conduitDown, x, y - conduitDown.getRegionHeight());
    }

    private void updateRect() {
        rectangleUp.set(x, y + WIDTH, conduitUp.getRegionWidth(), conduitUp.getRegionHeight());
        rectangleDown.set(x, y - conduitDown.getRegionHeight(), conduitDown.getRegionWidth(), conduitDown.getRegionHeight());
    }

    private void checkDead() {
        if (x < -rectangleUp.getWidth()) {
            setDead(true);
        }
    }

    public void move(float dx, float dy) {
        x += dx;
        y += dy;
        updateRect();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        updateRect();
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        updateRect();
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public boolean isCollision(Rectangle rect) {
        return rect.overlaps(rectangleUp) || rect.overlaps(rectangleDown);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
        if (isDead) {
            CollisionCtrl.removeCollisionAble(this, CollisionCtrl.GROUP.GROUP2);
        } else {
            CollisionCtrl.addCollisionAble(this, CollisionCtrl.GROUP.GROUP2);
        }
    }
}
