package com.yasserahmed.daggerpractice1.di;

import androidx.lifecycle.ViewModelProvider;

import com.yasserahmed.daggerpractice1.ViewModels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindviewmodelfactory(ViewModelProviderFactory modelProviderFactory);



}

