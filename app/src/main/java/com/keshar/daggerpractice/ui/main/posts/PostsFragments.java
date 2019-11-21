package com.keshar.daggerpractice.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keshar.daggerpractice.R;
import com.keshar.daggerpractice.models.Posts;
import com.keshar.daggerpractice.ui.main.Resource;
import com.keshar.daggerpractice.util.VerticalSpaceItemDecoration;
import com.keshar.daggerpractice.view_modules.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragments extends DaggerFragment {
    private static final String TAG = "PostsFragments";
    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    PostsRecyclerAdapter postsRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);
        subscribesObservers();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(postsRecyclerAdapter);
    }


    private void subscribesObservers() {
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Posts>>>() {
            @Override
            public void onChanged(Resource<List<Posts>> listResource) {
                if (listResource != null) {
                    Log.d(TAG, "onChanged: " + listResource.data);
                    switch (listResource.status) {
                        case ERROR: {
                            Log.e(TAG, "onChanged: Eroor::" + listResource.message);
                            break;
                        }
                        case SUCCESS: {
                            Log.d(TAG, "onChanged: get posts..,.");
                            postsRecyclerAdapter.setPosts(listResource.data);
                            break;
                        }
                        case LOADING: {
                            Log.d(TAG, "onChanged: Loading...");
                            break;
                        }
                    }
                }

            }
        });
    }
}
