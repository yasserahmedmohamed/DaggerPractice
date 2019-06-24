package com.yasserahmed.daggerpractice1.di.auth;

import androidx.lifecycle.ViewModel;

import com.yasserahmed.daggerpractice1.di.ViewModelKey;
import com.yasserahmed.daggerpractice1.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AtuhViewModelModule {
     @Binds
     @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public  abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
