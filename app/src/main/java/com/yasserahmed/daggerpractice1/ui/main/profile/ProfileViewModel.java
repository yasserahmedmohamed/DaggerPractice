package com.yasserahmed.daggerpractice1.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yasserahmed.daggerpractice1.Models.User;
import com.yasserahmed.daggerpractice1.SessionManager;
import com.yasserahmed.daggerpractice1.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;
    @Inject
   public ProfileViewModel(SessionManager sessionManager){
       Log.d(TAG,"Profile view model is working");
       this.sessionManager = sessionManager;
   }

   public LiveData<AuthResource<User>> GetUSerData(){
        return sessionManager.getUser();
   }
}
