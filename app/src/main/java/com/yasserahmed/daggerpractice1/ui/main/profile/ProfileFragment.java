package com.yasserahmed.daggerpractice1.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.yasserahmed.daggerpractice1.Models.User;
import com.yasserahmed.daggerpractice1.R;
import com.yasserahmed.daggerpractice1.ViewModels.ViewModelProviderFactory;
import com.yasserahmed.daggerpractice1.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";

    private ProfileViewModel profileViewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    private TextView email,username,website;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);

        profileViewModel = ViewModelProviders.of(this,providerFactory).get(ProfileViewModel.class);
        subcribeObservers();
    }

    private void subcribeObservers(){
        profileViewModel.GetUSerData().removeObservers(getViewLifecycleOwner());
        profileViewModel.GetUSerData().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource !=null){
                    switch (userAuthResource.status)
                    {
                        case AUTHENTICATED:
                            setUSerDetails(userAuthResource.data);
                            break;

                        case LOADING:
                            setErrorDetails(userAuthResource.message);
                            break;
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message) {
        email.setText(message);
        website.setText("error");
        username.setText("error");
    }

    private void setUSerDetails(User data) {
        email.setText(data.getEmail());
        website.setText(data.getWebsite());
        username.setText(data.getUsername());
    }
}
