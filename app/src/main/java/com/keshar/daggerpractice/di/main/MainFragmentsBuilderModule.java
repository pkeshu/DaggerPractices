package com.keshar.daggerpractice.di.main;


import com.keshar.daggerpractice.ui.main.posts.PostsFragments;
import com.keshar.daggerpractice.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragments contributePostsFragment();
}
