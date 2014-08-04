package com.cvte.game.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cvte.game.flappybird.com.RepeatTexture;
import com.cvte.game.flappybird.com.WorldObj;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cvtpc on 2014/7/18.
 */
public class MoveBG implements WorldObj {

    private static final float CONDUIT_DELAY = 1;

    private float speed;

    private TextureAtlas.AtlasRegion bgRegion;
    private TextureAtlas.AtlasRegion bgBottomRegion;

    private RepeatTexture repeatTexture;

    private ArrayList<Conduit> conduits;

    private TextureAtlas atlas;

    private float conduitDelay;

    public MoveBG(TextureAtlas atlas) {
        bgRegion = atlas.findRegion("background1");
        bgBottomRegion = atlas.findRegion("bottom");

        repeatTexture = new RepeatTexture(bgBottomRegion, 1, 2);
        speed = 0;

        conduits = new ArrayList<Conduit>(3);

        this.atlas = atlas;

        conduitDelay = 0;
    }

    public void reset(){
        for (Conduit conduit : conduits) {
            if (!conduit.isDead()) {
                conduit.setDead(true);
            }
        }
    }

    public void move() {
        repeatTexture.move(speed, 0);
        if (speed > 0) {
            if (repeatTexture.getRefX() > bgBottomRegion.getRegionWidth()) {
                repeatTexture.move(-bgBottomRegion.getRegionWidth(), 0);
            }
        } else {
            if (repeatTexture.getRefX() < -bgBottomRegion.getRegionWidth()) {
                repeatTexture.move(bgBottomRegion.getRegionWidth(), 0);
            }
        }
        for (Conduit conduit : conduits) {
            if (!conduit.isDead()) {
                conduit.move(speed, 0);
            }
        }
    }

    public void addConduit(float delta){
        conduitDelay += delta;
        if (conduitDelay > CONDUIT_DELAY) {
            conduitDelay -= CONDUIT_DELAY;
            Conduit conduit = findConduit();
            conduit.setX(300);
            conduit.setY(256);
        }
    }

    @Override
    public void update(float delta) {
        switch (BirdWorld.state) {
            case START:
                move();
                break;
            case PLAY:
                addConduit(delta);
                move();
                break;
            case FALL:
                break;
            case END:
                break;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(bgRegion, 0, 0);
        for (Conduit conduit : conduits) {
            if (!conduit.isDead()) {
                conduit.draw(batch);
            }
        }
        repeatTexture.draw(batch);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Conduit findConduit() {
        for (Conduit conduit : conduits) {
            if (conduit.isDead()) {
                conduit.setDead(false);
                return conduit;
            }
        }

        Conduit conduit = new Conduit(atlas, 0, 0);
        conduits.add(conduit);
        return conduit;
    }
}
