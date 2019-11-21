package com.keshar.daggerpractice.di.main;

import androidx.lifecycle.ViewModel;

import com.keshar.daggerpractice.di.ViewModelKey;
import com.keshar.daggerpractice.ui.main.posts.PostsViewModel;
import com.keshar.daggerpractice.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModel(PostsViewModel postsViewModel);
}
