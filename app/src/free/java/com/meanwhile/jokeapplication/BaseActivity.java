package com.meanwhile.jokeapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Juan on 11/01/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onStart() {
        super.onStart();

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setVisibility(View.GONE);
    }

    protected void setAdVisible(boolean visible){
        mAdView.setVisibility(visible?View.VISIBLE:View.GONE);
    }

}
