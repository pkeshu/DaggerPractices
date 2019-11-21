package com.keshar.daggerpractice.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.keshar.daggerpractice.SessionManager;
import com.keshar.daggerpractice.network.main.MainApi;

import javax.inject.Inject;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";

    final private SessionManager sessionManager;
    final private MainApi mainApi;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostsViewModel: is ready..");
    }
}
