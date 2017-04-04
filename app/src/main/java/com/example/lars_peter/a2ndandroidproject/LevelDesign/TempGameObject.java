package com.example.lars_peter.a2ndandroidproject.LevelDesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by SharkGaming on 30/03/2017.
 */

public abstract class TempGameObject
{

    private boolean traversable = false;
    private boolean isEnemy = false;

    // Most objects only have 1 frame
    // And don't need to bother with these
    private Animation anim = null;
    private boolean animated;
    private int animFps = 1;


    private Hitbox rectHitbox = new Hitbox();

    private float xVelocity;
    private float yVelocity;
    final int LEFT = -1;
    final int RIGHT = 1;
    private int facing;
    private boolean moves =false;


    private Vector2Point5D worldLocation;
    private float width;
    private float height;

    private boolean active = true;
    private boolean visible = true;
    private int animFrameCount = 1;
    private char type;

    private String bitmapName;

    public abstract void update(long _fps);

    public void setTraversable()
    {
        traversable = true;
    }

    public boolean isTraversable()
    {
        return traversable;
    }

    public void setIsEnemy()
    {
        isEnemy = true;
    }

    public boolean getIsEnemy()
    {
        return isEnemy;
    }

    public void setAnimFps(int _animFps)
    {
        this.animFps = _animFps;
    }

    public void setAnimFrameCount(int _animFrameCount)
    {
        this.animFrameCount = _animFrameCount;
    }

    public boolean isAnimated()
    {

        return animated;
    }

    public void setAnimated(Context _context, int _pixelsPerMetre, boolean _animated)
    {
        this.animated = _animated;
        this.anim = new Animation(_context, bitmapName,
                height,
                width,
                animFps,
                animFrameCount,
                _pixelsPerMetre );
    }

    public Rect getRectToDraw(long _deltaTime)
    {
        return anim.getCurrentFrame(_deltaTime, xVelocity, isMoves());
    }

    public void setRectHitbox()
    {
        rectHitbox.setTop(worldLocation.y);
        rectHitbox.setLeft(worldLocation.x);
        rectHitbox.setBottom(worldLocation.y + height);
        rectHitbox.setRight(worldLocation.x + width);
    }

    Hitbox getHitbox()
    {
        return rectHitbox;
    }

    public void setWorldLocationY(float _y)
    {
        this.worldLocation.y = _y;
    }

    public void setWorldLocationX(float _x)
    {
        this.worldLocation.x = _x;
    }

    void move(long _fps)
    {
        if(xVelocity != 0) {
            this.worldLocation.x += xVelocity / _fps;
        }

        if(yVelocity != 0) {
            this.worldLocation.y += yVelocity / _fps;
        }
    }

    public int getFacing()
    {
        return facing;
    }

    public void setFacing(int _facing)
    {
        this.facing = _facing;
    }

    public float getxVelocity()
    {
        return xVelocity;
    }

    public void setxVelocity(float _xVelocity)
    {
        // Only allow for objects that can move
        if(moves)
        {
            this.xVelocity = _xVelocity;
        }
    }

    public float getyVelocity()
    {
        return yVelocity;
    }

    public void setyVelocity(float _yVelocity)
    {
        // Only allow for objects that can move
        if(moves)
        {
            this.yVelocity = _yVelocity;
        }
    }

    public boolean isMoves()
    {
        return moves;
    }

    public void setMoves(boolean _moves)
    {
        this.moves = _moves;
    }

    public void setActive(boolean _active)
    {
        this.active = _active;
    }

    public String getBitmapName()
    {
        return bitmapName;
    }

    public Bitmap prepareBitmap(Context _context, String _bitmapName, int _pixelsPerMetre)
    {

        // Make a resource id from a String that is the same name as the .png
        int resID = _context.getResources().getIdentifier(_bitmapName,"drawable", _context.getPackageName());

        // Create the bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(_context.getResources(), resID);

        // Scale the bitmapSheet based on the number of pixels per metre
        // Multiply by the number of frames contained in the image file
        // Default 1 frame
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (width * animFrameCount * _pixelsPerMetre),
                                                   (int) (height * _pixelsPerMetre), false);

        return bitmap;
    }

    public Vector2Point5D _getWorldLocation()
    {
        return worldLocation;
    }

    public void setWorldLocation(float _x, float _y, int _z)
    {
        this.worldLocation = new Vector2Point5D();
        this.worldLocation.x = _x;
        this.worldLocation.y = _y;
        this.worldLocation.z = _z;
    }

    public void setBitmapName(String _bitmapName)
    {
        this.bitmapName = _bitmapName;
    }

    public float getWidth()
    {
        return width;
    }

    public void setWidth(float _width)
    {
        this.width = _width;
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float _height)
    {
        this.height = _height;
    }

    public boolean isActive()
    {
        return active;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean _visible)
    {
        this.visible = _visible;
    }

    public char getType()
    {
        return type;
    }

    public void setType(char _type)
    {
        this.type = _type;
    }
}
