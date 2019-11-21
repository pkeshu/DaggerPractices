package com.keshar.daggerpractice.di.main;

import com.keshar.daggerpractice.network.main.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainApi providerMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}
