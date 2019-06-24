package com.yasserahmed.daggerpractice1.di;

import android.app.Application;

import com.yasserahmed.daggerpractice1.BaseApplication;
import com.yasserahmed.daggerpractice1.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class,
                ViewModelFactoryModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    SessionManager SessionManager();

    @Component.Builder
    interface builder{

        @BindsInstance
        builder application(Application application);

        AppComponent build();
    }
}
