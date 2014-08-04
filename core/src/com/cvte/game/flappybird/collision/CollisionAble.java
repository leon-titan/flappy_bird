package com.cvte.game.flappybird.collision;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by cvtpc on 2014/7/20.
 */
public interface CollisionAble {
    public Rectangle getRect();
    public boolean isCollision(Rectangle rect);
}
