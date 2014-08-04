package com.cvte.game.flappybird.collision;

import java.util.ArrayList;

/**
 * Created by cvtpc on 2014/7/20.
 */
public class CollisionCtrl {

    public static enum GROUP {
        GROUP1,
        GROUP2,
    }

    private static ArrayList<CollisionListener> collisionListeners = new ArrayList<CollisionListener>();

    private static ArrayList<CollisionAble> collisionAbles1 = new ArrayList<CollisionAble>();
    private static ArrayList<CollisionAble> collisionAbles2 = new ArrayList<CollisionAble>();

    private static void onCollision(CollisionAble collisionAble1, CollisionAble collisionAble2) {
        for (CollisionListener collisionListener : collisionListeners) {
            collisionListener.onCollision(collisionAble1, collisionAble2);
        }
    }

    public static void update() {
        for (CollisionAble collisionAble1 : collisionAbles1) {
            for (CollisionAble collisionAble2 : collisionAbles2) {
                if (collisionAble2.isCollision(collisionAble1.getRect())) {
                    onCollision(collisionAble1, collisionAble2);
                }
            }
        }
    }

    public static void addCollisionAble(CollisionAble collisionAble, GROUP group) {
        switch (group) {
            case GROUP1:
                collisionAbles1.add(collisionAble);
                break;
            case GROUP2:
                collisionAbles2.add(collisionAble);
                break;
        }
    }

    public static void removeCollisionAble(CollisionAble collisionAble, GROUP group) {
        switch (group) {
            case GROUP1:
                collisionAbles1.remove(collisionAble);
                break;
            case GROUP2:
                collisionAbles2.remove(collisionAble);
                break;
        }
    }

    public static void addCollisionListener(CollisionListener collisionListener) {
        collisionListeners.add(collisionListener);
    }
}
