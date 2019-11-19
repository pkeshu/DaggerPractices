package com.keshar.daggerpractice.di;


import androidx.lifecycle.ViewModelProvider;

import com.keshar.daggerpractice.view_modules.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModuleFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelProviderFactory(ViewModelProviderFactory viewModelProviderFactory);
}
