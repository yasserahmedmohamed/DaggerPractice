package com.yasserahmed.daggerpractice1.di;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.yasserahmed.daggerpractice1.R;
import com.yasserahmed.daggerpractice1.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return  new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return  RequestOptions.placeholderOf(R.drawable.logologin)
                .error(R.drawable.primevetlogin);
    }
    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application,RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }
    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application)
    {
        return ContextCompat.getDrawable(application,R.drawable.logologin);
    }
}
