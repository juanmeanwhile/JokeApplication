package com.meanwhile.jokeapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.JavaJokes;
import com.example.Joke;
import com.example.mengujua.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.meanwhile.javajokesandroid.JokeActivity;
import com.meanwhile.jokeapplication.comms.GetJokeAsyncTask;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements GetJokeAsyncTask.GetJokeListener {

    private static final String TAG = "MainActivity";
    private static MyApi myApiService;
    private GetJokeAsyncTask mGetTask;
    private ProgressBar mLoadingView;
    private CoordinatorLayout mCoordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")  //Genymotion emulator
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCoordinator = (CoordinatorLayout) findViewById(R.id.coordinator);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoadingView.setVisibility(View.VISIBLE);

                if (mGetTask != null && !mGetTask.getStatus().equals(AsyncTask.Status.FINISHED)) {
                    mGetTask.cancel(true);
                }
                mGetTask = new GetJokeAsyncTask(myApiService, MainActivity.this);
                mGetTask.execute();
            }
        });

        mLoadingView = (ProgressBar) findViewById(R.id.loading);
        mLoadingView.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onJokeReceived(Joke joke) {
        mLoadingView.setVisibility(View.GONE);

        if (joke != null) {
            startActivity(JokeActivity.netIntent(MainActivity.this, joke));
        } else {
            Snackbar.make(mCoordinator, R.string.error_get_joke, Snackbar.LENGTH_SHORT).show();
        }
    }
}
