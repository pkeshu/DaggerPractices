package com.keshar.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.keshar.daggerpractice.SessionManager;
import com.keshar.daggerpractice.models.User;
import com.keshar.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;
    private SessionManager sessionManager;
//    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "AuthViewModel: View Model is working");
    }


    public void authenticateWithId(int userId) {
        Log.d(TAG, "authenticateWithId: attempting to login");
        sessionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId) {

        return LiveDataReactiveStreams.
                fromPublisher(authApi.getUsers(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if (user.getId() == -1)
                                    return AuthResource.error("Could not authentication", (User) null);
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io()));

    }

    public LiveData<AuthResource<User>> observerAuthState() {

        return sessionManager.getAuthUser();
    }
}
