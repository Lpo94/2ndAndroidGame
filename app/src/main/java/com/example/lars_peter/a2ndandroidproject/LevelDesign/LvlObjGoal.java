package com.example.lars_peter.a2ndandroidproject.LevelDesign;

/**
 * Created by SharkGaming on 30/03/2017.
 */

public class LvlObjGoal extends TempGameObject
{
    Location target;

    LvlObjGoal(float _worldStartX, float _worldStartY, char _type, Location _target)
    {
        final float HEIGHT = 2;
        final float WIDTH = 2;

        setHeight(HEIGHT);

        setWidth(WIDTH);

        setType(_type);

        setBitmapName("goal");

        this.target = new Location(_target.level, _target.x, _target.y);

        // Where does the tile start
        // X and y locations from constructor parameters
        setWorldLocation(_worldStartX, _worldStartY, 0);

        setRectHitbox();
    }

    public Location getTarget()
    {
        return target;
    }

    public void update(long _fps)
    {}
}
