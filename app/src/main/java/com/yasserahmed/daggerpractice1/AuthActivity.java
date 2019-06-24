package com.yasserahmed.daggerpractice1;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.yasserahmed.daggerpractice1.Models.User;
import com.yasserahmed.daggerpractice1.ViewModels.ViewModelProviderFactory;
import com.yasserahmed.daggerpractice1.ui.auth.AuthResource;
import com.yasserahmed.daggerpractice1.ui.auth.AuthViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;
    @Inject
    RequestManager requestManager;
    EditText edittext_userID;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        edittext_userID = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.login_button).setOnClickListener(this);
        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);

        setLogo();
        subscribeObserve();
    }

    private void subscribeObserve(){
       viewModel.observeUser().observe(this, new Observer<AuthResource<User>>() {
           @Override
           public void onChanged(AuthResource<User> userAuthResource) {
               if (userAuthResource != null)
               {
                   switch (userAuthResource.status){
                       case LOADING:{
                           SetprogressVisible(true);
                           break;
                       }
                       case ERROR:{
                           SetprogressVisible(false);
                           Toast.makeText(AuthActivity.this,userAuthResource.message,
                                   Toast.LENGTH_SHORT).show();
                           break;
                       }
                       case AUTHENTICATED:{
                           SetprogressVisible(false);
                           Toast.makeText(AuthActivity.this,userAuthResource.data.getEmail(),
                                   Toast.LENGTH_SHORT).show();
                           break;
                       }
                       case NOT_AUTHENTICATED:{
                           SetprogressVisible(false);

                           break;
                       }
                   }
               }
           }
       });
    }
    private void SetprogressVisible(boolean isvisible){
        if (isvisible)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);

    }

    private void setLogo(){
        requestManager.load(logo).into((ImageView)findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.login_button:{
                attempLogin();
                break;
            }
        }
    }
    private void attempLogin(){
        if (TextUtils.isEmpty(edittext_userID.getText().toString()))
        {
            Toast.makeText(AuthActivity.this,"Please enter user id",Toast.LENGTH_SHORT).show();
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(edittext_userID.getText().toString()));
    }
}
