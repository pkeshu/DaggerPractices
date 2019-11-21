package com.keshar.daggerpractice.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.keshar.daggerpractice.BaseActivity;
import com.keshar.daggerpractice.R;
import com.keshar.daggerpractice.SessionManager;
import com.keshar.daggerpractice.ui.main.posts.PostsFragments;
import com.keshar.daggerpractice.ui.main.profile.ProfileFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(this, "Main Activity", Toast.LENGTH_SHORT).show();
        testFragment();
    }

    private void testFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new PostsFragments())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout: {
                sessionManager.logout();
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}