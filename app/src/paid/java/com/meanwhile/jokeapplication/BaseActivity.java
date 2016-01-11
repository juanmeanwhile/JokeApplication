package com.meanwhile.jokeapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Juan on 11/01/2016.
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);

    }

    protected void setAdVisible(boolean visible){
       //ignore
    }

}
