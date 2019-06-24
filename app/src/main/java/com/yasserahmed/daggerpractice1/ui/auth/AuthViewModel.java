package com.yasserahmed.daggerpractice1.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.yasserahmed.daggerpractice1.Models.User;
import com.yasserahmed.daggerpractice1.SessionManager;
import com.yasserahmed.daggerpractice1.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private final String TAG = "Authviewmodel";
    private final AuthApi authApi;
    SessionManager sessionManager;
    @Inject
    public AuthViewModel(AuthApi authApi,SessionManager sessionManager){
        this.sessionManager = sessionManager;
        this.authApi = authApi;
        Log.d(TAG,"Viewmodel is working...");
    }
    public void authenticateWithId(int userId){
        sessionManager.authenticateWithid(queryUserId(userId));

    }

    private LiveData<AuthResource<User>> queryUserId(int userid){
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getuser(userid)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User erroruser = new User();
                                erroruser.setId(-1);
                                return erroruser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if (user.getId() == -1){
                                    return  AuthResource.error("colud not Authenticate",(User)null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<AuthResource<User>> obserAuthstate(){
        return sessionManager.getUser();
    }
}
