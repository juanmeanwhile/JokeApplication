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
import java.util.concurrent.ExecutionException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AndroidTestCase {
    public ApplicationTest() {
    }

    public void testGetJoke() {
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)

                .setRootUrl(Util.SERVER_URL)  //Genymotion emulator
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        MyApi myApiService = builder.build();
        String jokeSt = null;
        try {
            jokeSt = new GetJokeAsyncTask(myApiService, null).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertTrue(jokeSt != null);
    }
}