package com.meanwhile.jokeapplication;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;

import com.example.Joke;
import com.example.mengujua.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.meanwhile.jokeapplication.comms.GetJokeAsyncTask;

import java.io.IOException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AndroidTestCase {
    public ApplicationTest() {
    }

    public void testGetJoke() {
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

        MyApi myApiService = builder.build();
        GetJokeAsyncTask task = new GetJokeAsyncTask(myApiService, new GetJokeAsyncTask.GetJokeListener() {
            @Override
            public void onJokeReceived(Joke joke) {
                assertNotNull(joke);
                assertNotNull(joke.getJoke());
                assertTrue(joke.getJoke().isEmpty());
            }
        });
    }
}