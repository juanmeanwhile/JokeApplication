package com.meanwhile.javajokesandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.Joke;

public class JokeActivity extends AppCompatActivity {

    private static final String ARG_JOKE_TEXT = "joke_text";

    private Joke mJoke;
    private TextView mJokeView;

    public static Intent netIntent(Context context, Joke joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(ARG_JOKE_TEXT, joke.getJoke());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = getIntent().getStringExtra(ARG_JOKE_TEXT);
        mJoke = new Joke(joke);

        mJokeView = (TextView) findViewById(R.id.joke);
        mJokeView.setText(mJoke.getJoke());

    }
}
