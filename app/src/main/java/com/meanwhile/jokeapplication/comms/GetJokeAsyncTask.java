package com.meanwhile.jokeapplication.comms;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.example.Joke;
import com.example.mengujua.myapplication.backend.myApi.MyApi;
import com.meanwhile.javajokesandroid.JokeActivity;
import com.meanwhile.jokeapplication.R;

import java.io.IOException;


public class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "GetJokeAsyncTask";

    public interface GetJokeListener {
        void onJokeReceived(Joke joke);
    }

    private MyApi mApiService;
    private GetJokeListener mListener;

    public GetJokeAsyncTask(MyApi myApi, GetJokeListener listener) {
        mApiService = myApi;
        mListener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            return mApiService.getJoke().execute().getJoke();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }

        return null;
    }

    @Override
    public void onPostExecute(String result) {
        if (mListener != null) {
            mListener.onJokeReceived(result == null ? null : new Joke(result));
        }
    }
}
