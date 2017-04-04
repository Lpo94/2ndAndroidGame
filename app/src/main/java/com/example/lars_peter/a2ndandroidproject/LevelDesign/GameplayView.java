package com.example.lars_peter.a2ndandroidproject.LevelDesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by SharkGaming on 30/03/2017.
 */

public class GameplayView extends SurfaceView implements Runnable
{
    private volatile boolean running;
    private Thread gameThread = null;

    // For drawing
    private Paint paint;
    // Canvas could initially be local.
    // But later we will use it outside of the draw() method
    private Canvas canvas;
    private SurfaceHolder ourHolder;


    Context context;

    // Our new engine classes
    private LevelManager levelManager;
    private Viewport viewport;

    long startFrameTime;
    long timeThisFrame;
    long fps;

    GameplayView(Context _context, int _screenWidth, int _screenHeight)
    {
        super(_context);
        this.context = _context;

        // Initialize our drawing objects
        ourHolder = getHolder();
        paint = new Paint();

        // Initialize the viewport
        viewport = new Viewport(_screenWidth, _screenHeight);

        //loadLevel("LevelMountain", 118, 17);
        //loadLevel("LevelForest", 1, 17);
        //loadLevel("LevelCity", 118, 18);
        loadLevel("Level1", 1, 16);

    }

    public void loadLevel(String _level, float _px, float _py)
    {
        // Make the LevelManager null
        // As this method can be called at any time
        // Including when LevelManager is not null.
        levelManager = null;

        // Create a new LevelManager
        // Passing in a Context, screen details, level name and player location
        levelManager = new LevelManager(context, viewport.getPixelsPerMetreX(), _level, _px, _py);
    }

    @Override
    public void run()
    {

        while (running)
        {
            startFrameTime = System.currentTimeMillis();

            update();

            draw();

            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime;

            if (timeThisFrame >= 1)
            {
                fps = 1000 / timeThisFrame;
            }
        }
    }

    private void update()
    {

        for (TempGameObject go : levelManager.gameObjects)
        {
            if (go.isActive())
            {
                // Clip anything off-screen
                if (!viewport.clipObjects(go._getWorldLocation().x, go._getWorldLocation().y, go.getWidth(), go.getHeight()))
                {

                    // Set visible flag to true
                    go.setVisible(true);




                    if (levelManager.isPlaying())
                    {
                        go.update(fps);
                    }
                }
                else
                {
                    // Set visible flag to false
                    go.setVisible(false);
                    // Now draw() can ignore them
                }
            }
        }
    }


    private void draw()
    {

        if (ourHolder.getSurface().isValid())
        {
            //First we lock the area of memory we will be drawing to
            canvas = ourHolder.lockCanvas();

            // Rub out the last frame with arbitrary color
            paint.setColor(Color.argb(255, 0, 0, 0));
            canvas.drawColor(Color.argb(255, 0, 0, 0));

            // Draw all the GameObjects
            Rect toScreen2d = new Rect();

            // Draw a layer at a time
            for (int layer = -1; layer <= 1; layer++)
            {

                for (TempGameObject go : levelManager.gameObjects)
                {
                    if (go.isVisible() && go._getWorldLocation().z == layer)
                    { //Only draw if visible and this layer

                        toScreen2d.set(viewport.worldToScreen
                                (go._getWorldLocation().x,
                                        go._getWorldLocation().y,
                                        go.getWidth(),
                                        go.getHeight()));




                                // draw it the regular way round
                                canvas.drawBitmap(levelManager.bitmapsArray[levelManager.getBitmapIndex(go.getType())],
                                        go.getRectToDraw(System.currentTimeMillis()),
                                        toScreen2d, paint);
                        }
                        else
                        { // Just draw the whole bitmap
                            canvas.drawBitmap(levelManager.bitmapsArray[levelManager.getBitmapIndex(go.getType())],
                                    toScreen2d.left,
                                    toScreen2d.top, paint);
                        }
                    }
                }
            }

            // Unlock and draw the scene
            ourHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent)
    {
        if (levelManager != null)
        {
        }
        //invalidate();
        return true;
    }

    // Clean up our thread if the game is interrupted or the player quits
    public void pause()
    {
        running = false;
        try
        {
            gameThread.join();
        }
        catch (InterruptedException e) {
            Log.e("error", "failed to pause thread");
        }
    }

    // Make a new thread and start it
    // Execution moves to our run method
    public void resume()
    {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
