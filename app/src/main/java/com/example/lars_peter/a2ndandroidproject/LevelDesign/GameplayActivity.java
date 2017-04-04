package com.example.lars_peter.a2ndandroidproject.LevelDesign;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

/**
 * Created by SharkGaming on 30/03/2017.
 */
public class GameplayActivity extends Activity
{
    // Our object to handle the View
    private GameplayView gameplayView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();

        // Load the resolution into a Point object
        Point resolution = new Point();
        display.getSize(resolution);

        // And finally set the view for our game
        gameplayView = new GameplayView(this, resolution.x, resolution.y);

        // Make our platformView the view for the Activity
        setContentView(gameplayView);
    }

    // If the Activity is paused make sure to pause our thread
    @Override
    protected void onPause()
    {
        super.onPause();
        gameplayView.pause();
    }

    // If the Activity is resumed make sure to resume our thread
    @Override
    protected void onResume()
    {
        super.onResume();
        gameplayView.resume();
    }
}
