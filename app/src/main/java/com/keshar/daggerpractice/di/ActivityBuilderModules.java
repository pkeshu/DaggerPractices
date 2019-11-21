package com.keshar.daggerpractice.di;


import com.keshar.daggerpractice.di.auth.AuthModule;
import com.keshar.daggerpractice.di.auth.AuthViewModelsModule;
import com.keshar.daggerpractice.ui.auth.AuthActivity;
import com.keshar.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModules {

    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelsModule.class,
                    AuthModule.class,
            }
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

}
