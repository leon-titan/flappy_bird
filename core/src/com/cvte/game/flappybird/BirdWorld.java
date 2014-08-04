package com.cvte.game.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cvte.game.flappybird.collision.CollisionAble;
import com.cvte.game.flappybird.collision.CollisionCtrl;
import com.cvte.game.flappybird.collision.CollisionListener;
import com.cvte.game.flappybird.com.World;
import com.cvte.game.flappybird.com.WorldObj;
import com.cvte.game.flappybird.input.Input;

import java.util.ArrayList;

/**
 * Created by cvtpc on 2014/7/18.
 */
public class BirdWorld implements World {

    private static final float BG_SPEED = -3f;

    private ArrayList<WorldObj> worldObjs;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private MoveBG bg;
    private Bird bird;

    private Input input;

    public static enum STATE {
        START,
        PLAY,
        FALL,
        END,
        RESTART,
    }

    public static STATE state;

    public BirdWorld() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 288, 512);

        batch = new SpriteBatch();

        worldObjs = new ArrayList<WorldObj>();

        TextureAtlas atlas;
        atlas = new TextureAtlas(Gdx.files.internal("atlas.txt"));

        bg = new MoveBG(atlas);
        bg.setSpeed(BG_SPEED);

        bird = new Bird(atlas);
        addObj(bird);

        input = new Input(bird);

        Gdx.input.setInputProcessor(input);

        state = STATE.START;

        CollisionCtrl.addCollisionListener(new CollisionListener() {
            @Override
            public void onCollision(CollisionAble collisionAble1, CollisionAble collisionAble2) {
                if (state == STATE.PLAY) {
                    state = STATE.FALL;
                }
            }
        });
    }

    private void restart() {
        bird.setY(260);
        bird.setRotation(0);

        bg.reset();

        state = STATE.START;
    }

    @Override
    public void updateWorld(float delta) {
        bg.update(delta);
        for (WorldObj obj : worldObjs) {
            obj.update(delta);
        }

        if (state == STATE.RESTART) {
            restart();
        } else if (state == STATE.PLAY) {
            CollisionCtrl.update();
        }
    }

    @Override
    public void drawWorld() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        bg.draw(batch);
        for (WorldObj obj : worldObjs) {
            obj.draw(batch);
        }

        batch.end();
    }

    public void addObj(WorldObj obj) {
        worldObjs.add(obj);
    }
}
