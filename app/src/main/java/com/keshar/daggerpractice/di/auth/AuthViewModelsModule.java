package com.keshar.daggerpractice.di.auth;


import androidx.lifecycle.ViewModel;

import com.keshar.daggerpractice.di.ViewModelKey;
import com.keshar.daggerpractice.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);

}
