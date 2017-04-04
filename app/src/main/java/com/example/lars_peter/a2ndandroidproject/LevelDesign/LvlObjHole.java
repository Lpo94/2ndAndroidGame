package com.example.lars_peter.a2ndandroidproject.LevelDesign;

import android.content.Context;

/**
 * Created by SharkGaming on 30/03/2017.
 */

public class LvlObjHole extends TempGameObject
{

    LvlObjHole(float _worldStartX, float _worldStartY, char _type)
    {
        setTraversable();
        setIsEnemy();

        final float HEIGHT = 1;
        final float WIDTH = 1;

        setHeight(HEIGHT);
        setWidth(WIDTH);

        setType(_type);

        setBitmapName("hole");

        setWorldLocation(_worldStartX, _worldStartY, 0);

        setRectHitbox();
    }

    public void update(long _fps)
    {
    }
}
