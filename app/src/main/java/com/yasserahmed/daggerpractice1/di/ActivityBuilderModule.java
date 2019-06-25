package com.yasserahmed.daggerpractice1.di;

import com.yasserahmed.daggerpractice1.AuthActivity;
import com.yasserahmed.daggerpractice1.di.auth.AtuhViewModelModule;
import com.yasserahmed.daggerpractice1.di.auth.AuthModule;
import com.yasserahmed.daggerpractice1.di.main.MainFragmentBuilderModule;
import com.yasserahmed.daggerpractice1.di.main.MainViewModelModule;
import com.yasserahmed.daggerpractice1.ui.main.MainActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {AtuhViewModelModule.class, AuthModule.class})
    abstract AuthActivity constributeAuthActivity();

    @ContributesAndroidInjector(modules = {MainFragmentBuilderModule.class, MainViewModelModule.class})
    abstract MainActivity constributeMAinActivity();


}
