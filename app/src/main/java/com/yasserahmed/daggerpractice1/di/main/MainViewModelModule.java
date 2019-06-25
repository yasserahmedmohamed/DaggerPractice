package com.yasserahmed.daggerpractice1.di.main;

import androidx.lifecycle.ViewModel;

import com.yasserahmed.daggerpractice1.di.ViewModelKey;
import com.yasserahmed.daggerpractice1.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

}
