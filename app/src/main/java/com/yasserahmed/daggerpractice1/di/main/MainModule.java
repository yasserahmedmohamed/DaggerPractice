package com.yasserahmed.daggerpractice1.di.main;

import androidx.recyclerview.widget.RecyclerView;

import com.yasserahmed.daggerpractice1.adapters.PostsRecyclerAdapter;
import com.yasserahmed.daggerpractice1.network.main.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static PostsRecyclerAdapter provideAdapter(){
        return new PostsRecyclerAdapter();
    }
    @Provides
    static MainApi providesMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}
