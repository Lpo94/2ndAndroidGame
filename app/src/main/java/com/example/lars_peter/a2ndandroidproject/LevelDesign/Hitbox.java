package com.example.lars_peter.a2ndandroidproject.LevelDesign;

/**
 * Created by SharkGaming on 30/03/2017.
 */

public class Hitbox
{
    float top;
    float left;
    float bottom;
    float right;
    float height;

    boolean intersects(Hitbox _hitbox)
    {
        boolean hit = false;

        if(this.right > _hitbox.left && this.left < _hitbox.right )
        {
            if(this.top < _hitbox.bottom && this.bottom > _hitbox.top )
            {
                return true;
            }
        }
        return false;
    }

    public void setTop(float _top)
    {
        this.top = _top;
    }

    public void setBottom(float _bottom)
    {
        this.bottom = _bottom;
    }

    public float getLeft()
    {
        return left;
    }

    public void setLeft(float _left)
    {
        this.left = _left;
    }

    public float getRight()
    {
        return right;
    }

    public void setRight(float _right)
    {
        this.right = _right;
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float _height)
    {
        this.height = _height;
    }
}
