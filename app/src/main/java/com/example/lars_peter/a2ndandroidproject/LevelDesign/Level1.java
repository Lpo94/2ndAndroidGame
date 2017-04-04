package com.example.lars_peter.a2ndandroidproject.LevelDesign;

import java.util.ArrayList;

/**
 * Created by SharkGaming on 30/03/2017.
 */

public class Level1 extends LevelData
{
    // Tile types
    // . = no tile
    // p = Player
    // w = Wall
    // h = Hole
    // g = Goal



    Level1()
    {
        tiles = new ArrayList<String>();
        this.tiles.add("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        this.tiles.add("wp...w......w..........................................................................................................w");
        this.tiles.add("w....w.................................................................................................................w");
        this.tiles.add("w....w.................................................................................................................w");
        this.tiles.add("w....w......w......wwwwwww.............................................................................................w");
        this.tiles.add("w...........w..........................................................................................................w");
        this.tiles.add("w...........w..........................................................................................................w");
        this.tiles.add("w....wwwwwwww..........................................................................................................w");
        this.tiles.add("w....w.................................................................................................................w");
        this.tiles.add("w....w......wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww...................................w.");
        this.tiles.add("w......................................................................................................................w");
        this.tiles.add("w......................................................................................................................w");
        this.tiles.add("wwwwww.................................................................................................................w");
        this.tiles.add("w......................................................................................................................w");
        this.tiles.add("w......................................................................................................................w");
        this.tiles.add("w......................................................................................................................w");
        this.tiles.add("w......................................................................................................................w");
        this.tiles.add("w......................................................................................................................w");
        this.tiles.add("w......................................................................................................................w");
        this.tiles.add("w......................................................................................................................w");
        this.tiles.add("w......................................................................................................................g");
        this.tiles.add("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");


        // Declare the values for the teleports in order of appearance
        locations = new ArrayList<Location>();

        this.locations.add(new Location("Level2", 1f, 17f));

    }
}
