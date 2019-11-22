package com.keshar.daggerpractice.di.main;

import com.keshar.daggerpractice.network.main.MainApi;
import com.keshar.daggerpractice.ui.main.posts.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static PostsRecyclerAdapter provideRecyclerAdapter() {
        return new PostsRecyclerAdapter();
    }

    @MainScope
    @Provides
    static MainApi providerMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
