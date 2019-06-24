package com.yasserahmed.daggerpractice1.di.auth;

import com.yasserahmed.daggerpractice1.network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit){
        return  retrofit.create(AuthApi.class);
    }
}
