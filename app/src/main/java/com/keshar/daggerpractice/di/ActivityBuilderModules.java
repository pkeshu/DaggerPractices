package com.keshar.daggerpractice.di;


import com.keshar.daggerpractice.di.auth.AuthViewModelsModule;
import com.keshar.daggerpractice.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModules {

    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelsModule.class,
            }
    )
    abstract AuthActivity contributeAuthActivity();

}
