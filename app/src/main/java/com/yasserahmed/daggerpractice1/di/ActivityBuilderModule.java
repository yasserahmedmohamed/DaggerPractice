package com.yasserahmed.daggerpractice1.di;

import com.yasserahmed.daggerpractice1.AuthActivity;
import com.yasserahmed.daggerpractice1.di.auth.AtuhViewModelModule;
import com.yasserahmed.daggerpractice1.di.auth.AuthModule;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {AtuhViewModelModule.class, AuthModule.class})
    abstract AuthActivity constributeAuthActivity();



}
