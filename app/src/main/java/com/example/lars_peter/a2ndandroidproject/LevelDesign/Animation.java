package com.example.lars_peter.a2ndandroidproject.LevelDesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by SharkGaming on 30/03/2017.
 */

public class Animation {
    Bitmap bitmapSheet;
    String bitmapName;
    private Rect sourceRect;
    private int frameCount;
    private int currentFrame;
    private long frameTicker;
    private int framePeriod;
    private int frameWidth;
    private int frameHeight;
    int pixelsPerMetre;

    Animation(Context _context, String _bitmapName, float _frameHeight, float _frameWidth,
              int _animFps, int _frameCount, int _pixelsPerMetre) {
        this.currentFrame = 0;
        this.frameCount = _frameCount;

        this.frameWidth = (int) _frameWidth * _pixelsPerMetre;
        this.frameHeight = (int) _frameHeight * _pixelsPerMetre;
        sourceRect = new Rect(0, 0, this.frameWidth, this.frameHeight);
        framePeriod = 1000 / _animFps;
        frameTicker = 0l;
        this.bitmapName = "" + _bitmapName;
        this.pixelsPerMetre = _pixelsPerMetre;
    }

    public Rect getCurrentFrame(long _time, float _xVelocity, boolean _moves) {

        if (_xVelocity != 0 || _moves == false)
        {// Only animate if the object is moving or it is an object which doesn't move
            if (_time > frameTicker + framePeriod)
            {
                frameTicker = _time;
                currentFrame++;

                if (currentFrame >= frameCount)
                {
                    //if (currentFrame >= frameCount) {
                    currentFrame = 0;
                }
            }
        }
        //update the left and right values of the source of
        //the next frame on the spritesheet
        this.sourceRect.left = currentFrame * frameWidth;
        this.sourceRect.right = this.sourceRect.left + frameWidth;

        return sourceRect;
    }

}
