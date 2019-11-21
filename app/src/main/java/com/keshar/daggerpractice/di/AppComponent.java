package com.keshar.daggerpractice.di;


import android.app.Application;

import com.keshar.daggerpractice.BaseApplication;
import com.keshar.daggerpractice.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModules.class,
                AppModule.class,
                ViewModuleFactoryModule.class,
        }
)

public interface AppComponent extends AndroidInjector<BaseApplication> {

    SessionManager sessionManager();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }
}
