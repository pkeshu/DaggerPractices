package com.keshar.daggerpractice.di;


import com.keshar.daggerpractice.di.auth.AuthModule;
import com.keshar.daggerpractice.di.auth.AuthScope;
import com.keshar.daggerpractice.di.auth.AuthViewModelsModule;
import com.keshar.daggerpractice.di.main.MainFragmentsBuilderModule;
import com.keshar.daggerpractice.di.main.MainModule;
import com.keshar.daggerpractice.di.main.MainScope;
import com.keshar.daggerpractice.di.main.MainViewModelsModule;
import com.keshar.daggerpractice.ui.auth.AuthActivity;
import com.keshar.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModules {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelsModule.class,
                    AuthModule.class,
            }
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentsBuilderModule.class, MainViewModelsModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();

}
