package com.yasserahmed.daggerpractice1.di;

import android.app.Application;

import com.yasserahmed.daggerpractice1.BaseApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {


    @Component.Builder
    interface builder{

        @BindsInstance
        builder application(Application application);

        AppComponent build();
    }
}
