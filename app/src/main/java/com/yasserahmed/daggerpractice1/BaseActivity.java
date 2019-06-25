package com.yasserahmed.daggerpractice1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.yasserahmed.daggerpractice1.Models.User;
import com.yasserahmed.daggerpractice1.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObserver();

    }
    private void subscribeObserver(){
        sessionManager.getUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null)
                {
                    switch (userAuthResource.status){
                        case LOADING:{
                            break;
                        }
                        case ERROR:{

                            break;
                        }
                        case AUTHENTICATED:{
                            Log.d(TAG,"login done");
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            ReturnToLogin();
                            break;
                        }
                    }
                }
            }
        });
    }
    private void ReturnToLogin(){
        Intent intent =new Intent(this,AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
