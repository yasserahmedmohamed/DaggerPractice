package com.yasserahmed.daggerpractice1.di.main;

import com.yasserahmed.daggerpractice1.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
     abstract ProfileFragment ContributeProfileFragment();
}
