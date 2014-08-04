package com.cvte.game.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.cvte.game.flappybird.collision.CollisionAble;
import com.cvte.game.flappybird.collision.CollisionCtrl;
import com.cvte.game.flappybird.com.WorldObj;

/**
 * Created by cvtpc on 2014/7/19.
 */
public class Bird implements WorldObj, CollisionAble {

    private static final float GROUND_Y = 110F;
    private static final float TOP_Y_SPEED = 5.5f;
    private static final float TOP_ANGLE = 40;

    private Animation birdAnim;
    private float stateTime;

    private float x;
    private float y;
    private float ySpeed;
    private float yDSpeed;

    private float rotation;

    private Rectangle birdRect;

    public Bird(TextureAtlas atlas) {
        TextureRegion frame1 = atlas.findRegion("bird/BBird1");
        TextureRegion frame2 = atlas.findRegion("bird/BBird2");
        TextureRegion frame3 = atlas.findRegion("bird/BBird3");
        birdAnim = new Animation(0.08f, frame1, frame2, frame3);
        birdAnim.setPlayMode(Animation.PlayMode.LOOP);

        stateTime = 0;

        x = 60;
        y = 260;
        ySpeed = 0;
        yDSpeed = -0.3f;
        rotation = 0;

        birdRect = new Rectangle();
        updateRect();

        CollisionCtrl.addCollisionAble(this, CollisionCtrl.GROUP.GROUP1);
    }

    public void drop() {
        if (y > GROUND_Y) {
            y += ySpeed;
            ySpeed += yDSpeed;
        } else {
            return;
        }

        if (y < GROUND_Y) {
            y = GROUND_Y;
            BirdWorld.state = BirdWorld.STATE.END;
        }

        rotation = ySpeed * TOP_ANGLE / TOP_Y_SPEED;
        if (rotation > TOP_ANGLE) {
            rotation = TOP_ANGLE;
        } else if (rotation < -90) {
            rotation = -90;
        }
        updateRect();
    }

    private void updateRect() {
        birdRect.set(x, y, 35, 30);
    }

    @Override
    public void update(float delta) {
        switch (BirdWorld.state) {
            case START:
                stateTime += Gdx.graphics.getDeltaTime();
                break;
            case PLAY:
                stateTime += Gdx.graphics.getDeltaTime();
                drop();
                break;
            case FALL:
                drop();
                break;
            case END:
                break;
        }
    }

    public void fly() {
        ySpeed = TOP_Y_SPEED;
    }

    @Override
    public void draw(SpriteBatch batch) {
        TextureRegion region = birdAnim.getKeyFrame(stateTime);
//    public void draw (TextureRegion region, float x, float y, float originX, float originY, float width, float height,
//                      float scaleX, float scaleY, float rotation) {
        batch.draw(region, x, y, region.getRegionWidth() >> 1, region.getRegionHeight() >> 1, region.getRegionWidth(), region.getRegionHeight(), 1, 1, rotation);
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
        return birdRect;
    }

    @Override
    public boolean isCollision(Rectangle rect) {
        return birdRect.overlaps(rect);
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
