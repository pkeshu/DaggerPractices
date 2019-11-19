package com.keshar.daggerpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";

//    @Inject
//    String testString;
//
//    @Inject
//    boolean isApplicationNull;

    @Inject
    Drawable logo;

    @Inject
    RequestManager glideInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
//        Log.d(TAG, "onCreate: " + testString);
//        Log.d(TAG, "onCreate: Is Application is null?" + isApplicationNull);
        setLogo();
    }

    private void setLogo() {
        glideInstance
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }
}
