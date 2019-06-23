package com.yasserahmed.daggerpractice1.di;

import com.yasserahmed.daggerpractice1.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract AuthActivity constributeAuthActivity();



}
