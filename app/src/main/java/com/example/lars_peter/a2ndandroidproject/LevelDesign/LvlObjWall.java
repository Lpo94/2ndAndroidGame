package com.example.lars_peter.a2ndandroidproject.LevelDesign;

/**
 * Created by SharkGaming on 30/03/2017.
 */

public class LvlObjWall extends TempGameObject
{
    LvlObjWall(float _worldStartX, float _worldStartY, char _type)
    {
        final float HEIGHT = 1;
        final float WIDTH = 1;

        setHeight(HEIGHT);
        setWidth(WIDTH);

        setType(_type);

        setBitmapName("wall");

        setWorldLocation(_worldStartX, _worldStartY, 0);

        setRectHitbox();
    }

    public void update(long _fps)
    {
    }
}


