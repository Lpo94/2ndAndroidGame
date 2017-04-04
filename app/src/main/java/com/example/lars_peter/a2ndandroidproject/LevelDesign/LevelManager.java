package com.example.lars_peter.a2ndandroidproject.LevelDesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by SharkGaming on 30/03/2017.
 */

public class LevelManager
{
    private String level;
    int mapWidth;
    int mapHeight;

    LvlObjTmpPlayer player;
    int playerIndex;

    private boolean playing;

    LevelData levelData;
    ArrayList<TempGameObject> gameObjects;

    Bitmap[] bitmapsArray;

    public LevelManager(Context _context, int _pixelsPerMetre, String _level, float _px, float _py)
    {
        this.level = _level;

        switch (level)
        {
            case "Level1":
                levelData = new Level1();
                break;

        }

        gameObjects = new ArrayList<>();
        bitmapsArray = new Bitmap[10];

        loadMapData(_context, _pixelsPerMetre, _px, _py);

        //playing = true;
    }


    public void switchPlayingStatus()
    {
        playing = !playing;
        if (playing)
        {
            // Gyro til
        }
        else
        {
            // Gyro fra
        }
    }


    public boolean isPlaying()
    {
        return playing;
    }



    public Bitmap getBitmap(char _blockType)
    {

        int index;
        switch (_blockType)
        {

            // Empty
            case '.':
                index = 0;
                break;

            // Player
            case 'p':
                index = 1;
                break;

            // Wall
            case 'w':
                index = 2;
                break;

            // Hole
            case 'h':
                index = 3;
                break;

            // Goal
            case 'g':
                index = 4;
                break;

            default:
                index = 0;
                break;
        }

        return bitmapsArray[index];
    }


    public int getBitmapIndex(char _blockType)
    {

        int index;
        switch (_blockType)
        {
            // Empty
            case '.':
                index = 0;
                break;

            // Player
            case 'p':
                index = 1;
                break;

            // Wall
            case 'w':
                index = 2;
                break;

            // Hole
            case 'h':
                index = 3;
                break;

            // Goal
            case 'g':
                index = 4;
                break;

            default:
                index = 0;
                break;
        }

        return index;
    }


    void loadMapData(Context _context, int _pixelsPerMetre, float _px, float _py)
    {
        char c;

        //Keep track of where we load our game objects
        int currentIndex = -1;
        int teleportIndex = -1;
        // how wide and high is the map? Viewport needs to know
        mapHeight = levelData.tiles.size();
        mapWidth = levelData.tiles.get(0).length();

        for (int i = 0; i < levelData.tiles.size(); i++)
        {
            for (int j = 0; j < levelData.tiles.get(i).length(); j++)
            {
                c = levelData.tiles.get(i).charAt(j);

                if (c != '.')
                {// Don't want to load the empty spaces
                    currentIndex++;
                    switch (c)
                    {
                        // Player
                        case 'p':
                            gameObjects.add(new LvlObjTmpPlayer(j, i, c));
                            // We want the index of the player
                            playerIndex = currentIndex;
                            // We want a reference to the player object
                            player = (LvlObjTmpPlayer) gameObjects.get(playerIndex);

                            break;

                        // Wall
                        case 'w':
                            gameObjects.add(new LvlObjWall(j, i, c));
                            break;

                        // Hole
                        case 'h':
                            teleportIndex++;
                            gameObjects.add(new LvlObjHole(j, i, c));
                            break;

                        // Goal
                        case 'g':
                            teleportIndex++;
                            gameObjects.add(new LvlObjGoal(j, i, c, levelData.locations.get(teleportIndex)));
                            break;
                    }

                    // If the bitmap isn't prepared yet
                    if (bitmapsArray[getBitmapIndex(c)] == null)
                    {
                        // Prepare it now and put it in the bitmapsArrayList
                        bitmapsArray[getBitmapIndex(c)] = gameObjects.get(currentIndex).prepareBitmap(_context, gameObjects.get(currentIndex).getBitmapName(), _pixelsPerMetre);
                    }
                }
            }
        }
    }
}
