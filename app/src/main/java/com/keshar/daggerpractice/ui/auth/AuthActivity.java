package com.keshar.daggerpractice.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.keshar.daggerpractice.R;
import com.keshar.daggerpractice.models.User;
import com.keshar.daggerpractice.view_modules.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

//    @Inject
//    String testString;
//
//    @Inject
//    boolean isApplicationNull;

    private AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager glideInstance;

    private EditText userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
//        Log.d(TAG, "onCreate: " + testString);
//        Log.d(TAG, "onCreate: Is Application is null?" + isApplicationNull);
        userId = findViewById(R.id.user_id_input);
        ((Button) findViewById(R.id.login_button)).setOnClickListener(this);
        setLogo();
        subscribeObservers();
    }


    private void subscribeObservers(){
        viewModel.observeUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user!=null)
                    Log.d(TAG, "onChanged: "+user.getEmail());
            }
        });
    }

    private void setLogo() {
        glideInstance
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button: {
                attemptLogin();
                break;
            }
        }
    }

    private void attemptLogin() {
        if (TextUtils.isEmpty(userId.getText().toString()))
            return;
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }
}
