package com.keshar.daggerpractice.di;


import com.keshar.daggerpractice.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModules {

    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();

    @Provides
    static String provideString() {
        return "This is the test String";
    }

}
