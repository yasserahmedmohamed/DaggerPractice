package com.yasserahmed.daggerpractice1.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.yasserahmed.daggerpractice1.Models.User;
import com.yasserahmed.daggerpractice1.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private final String TAG = "Authviewmodel";
    private final AuthApi authApi;
    private MediatorLiveData<AuthResource<User>> authuser = new MediatorLiveData<>();
    @Inject
    public AuthViewModel(AuthApi authApi){
        this.authApi = authApi;
        Log.d(TAG,"Viewmodel is working...");
    }
    public void authenticateWithId(int userId){

        authuser.setValue(AuthResource.loading((User)null));

        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
             authApi.getuser(userId)
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
        authuser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> user) {
                authuser.setValue(user);
                authuser.removeSource(source);
            }
        });
    }
    public LiveData<AuthResource<User>> observeUser(){
        return authuser;
    }
}
