package com.cvte.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class FlappyBirdGame extends ApplicationAdapter {

    private BirdWorld birdWorld;

    @Override
	public void create () {
        birdWorld = new BirdWorld();
    }

	@Override
	public void render () {
        birdWorld.updateWorld(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        birdWorld.drawWorld();
	}
}
