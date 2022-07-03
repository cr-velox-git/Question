package com.noobdev.worthyking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.noobdev.worthyking.questions.WorthyKing;


public class MainActivity extends AppCompatActivity implements Interface.MainInterface {
    String TAG = "mainActivity";

    WorthyKing worthyKing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        worthyKing = new WorthyKing(this);
        worthyKing.initialClass();

    }

    @Override
    public void onGetWorthyKing(int days) {
        Log.d(TAG,"Worthy King: "+ (days));
    }
}