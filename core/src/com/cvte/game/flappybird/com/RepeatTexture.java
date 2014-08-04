package com.cvte.game.flappybird.com;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RepeatTexture {

	private int row;
	private int col;
	
	private float intervalH;
	private float intervalV;
	
	private float x;
	private float y;

    private TextureRegion textureRegion;

	public RepeatTexture(TextureRegion textureRegion, int row, int col) {
		int i, j;
		
		this.row = row;
		this.col = col;

        intervalV = 0;
        intervalH = 0;

        this.textureRegion = textureRegion;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

    public float getRefX(){
        return x;
    }

    public float getRefY(){
        return y;
    }

	public void setRefX(float x) {
		this.x = x;
	}

	public void setRefY(float y) {
		this.y = y;
	}

	public void setRefPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

    public void draw(SpriteBatch batch) {
        int i, j;

        for(i = 0; i < row; ++i){
            for(j = 0; j < col; ++j){
                batch.draw(textureRegion, x + j * (textureRegion.getRegionWidth() + intervalH),
                        y + i * (textureRegion.getRegionHeight() + intervalV));
            }
        }
    }

	public void move(float dx, float dy) {
        x += dx;
        y += dy;
	}

	public float getIntervalH() {
		return intervalH;
	}

	public void setIntervalH(float intervalH) {
		this.intervalH = intervalH;
	}

	public float getIntervalV() {
		return intervalV;
	}

	public void setIntervalV(float intervalV) {
		this.intervalV = intervalV;
	}
}
