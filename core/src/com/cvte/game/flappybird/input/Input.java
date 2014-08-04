package com.cvte.game.flappybird.input;

import com.badlogic.gdx.InputAdapter;
import com.cvte.game.flappybird.Bird;
import com.cvte.game.flappybird.BirdWorld;

/**
 * Created by cvtpc on 2014/7/19.
 */
public class Input extends InputAdapter {

    private Bird bird;

    public Input(Bird bird) {
        this.bird = bird;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        switch (BirdWorld.state) {
            case START:
                BirdWorld.state = BirdWorld.STATE.PLAY;
            case PLAY:
                bird.fly();
                break;
            case FALL:
                break;
            case END:
                BirdWorld.state = BirdWorld.STATE.RESTART;
                break;
        }

        return true;
    }
}
