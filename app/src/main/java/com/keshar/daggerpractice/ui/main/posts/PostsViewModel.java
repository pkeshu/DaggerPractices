package com.keshar.daggerpractice.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.keshar.daggerpractice.SessionManager;
import com.keshar.daggerpractice.models.Posts;
import com.keshar.daggerpractice.network.main.MainApi;
import com.keshar.daggerpractice.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";

    final private SessionManager sessionManager;
    final private MainApi mainApi;

    private MediatorLiveData<Resource<List<Posts>>> posts;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostsViewModel: is ready..");
    }

    public LiveData<Resource<List<Posts>>> observePosts() {
        if (posts == null) {
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Posts>) null));

            final LiveData<Resource<List<Posts>>> source = LiveDataReactiveStreams
                    .fromPublisher(
                            mainApi.getPostsFromUser(sessionManager.getAuthUser().getValue().data.getId())
                                    .onErrorReturn(new Function<Throwable, List<Posts>>() {
                                        @Override
                                        public List<Posts> apply(Throwable throwable) throws Exception {
                                            Log.d(TAG, "apply: " + throwable);
                                            Posts errorPosts = new Posts();
                                            errorPosts.setId(-1);
                                            ArrayList<Posts> posts = new ArrayList<>();
                                            posts.add(errorPosts);
                                            return posts;
                                        }
                                    })
                                    .map(new Function<List<Posts>, Resource<List<Posts>>>() {
                                        @Override
                                        public Resource<List<Posts>> apply(List<Posts> posts) throws Exception {
                                            if (posts.size() > 0) {
                                                if (posts.get(0).getId() == -1) {
                                                    return Resource.error("Somethig went wrong ", ((List<Posts>) null));
                                                }
                                            }
                                            return Resource.success(posts);
                                        }
                                    })
                                    .subscribeOn(Schedulers.io())
                    );

            posts.addSource(source, new Observer<Resource<List<Posts>>>() {
                @Override
                public void onChanged(Resource<List<Posts>> listResource) {
                    posts.setValue(listResource);
                    posts.removeSource(source);
                }
            });

        }
        return posts;
    }
}
