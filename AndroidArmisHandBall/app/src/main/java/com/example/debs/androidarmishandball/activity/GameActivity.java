package com.example.debs.androidarmishandball.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.debs.androidarmishandball.R;

public class GameActivity extends AppCompatActivity {

    private Bundle mGameInfo;
    public GameActivity(Bundle gameInfo) {
        mGameInfo = gameInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}
