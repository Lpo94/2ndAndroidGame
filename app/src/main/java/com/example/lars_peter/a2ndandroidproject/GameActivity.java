package com.example.lars_peter.a2ndandroidproject;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends Activity {

    private ViewTrue gameView;
    @Override
    protected void onCreate(Bundle savedInstance){

        super.onCreate(savedInstance);
        gameView = new ViewTrue(this);
        setContentView(gameView);
    }

    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }


    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }

}
