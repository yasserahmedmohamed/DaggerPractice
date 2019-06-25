package com.yasserahmed.daggerpractice1.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.yasserahmed.daggerpractice1.Models.Posts;
import com.yasserahmed.daggerpractice1.SessionManager;
import com.yasserahmed.daggerpractice1.network.main.MainApi;
import com.yasserahmed.daggerpractice1.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    private final SessionManager sessionManager;
    private final MainApi mainApi;
    private static final String TAG = "PostViewModel";
    private MediatorLiveData<Resource<List<Posts>>>Mainposts;
    @Inject
   public PostViewModel(SessionManager sessionManager,MainApi mainApi){
        this.sessionManager=sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG,"Post view models is working");
    }

    public LiveData<Resource<List<Posts>>> ObservePosts(){
        if (Mainposts == null)
        {
            Mainposts = new MediatorLiveData<>();
            Mainposts.setValue(Resource.loading((List<Posts>)null));
            final LiveData<Resource<List<Posts>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPostForUser(sessionManager.getUser().getValue().data.getId())
                    .onErrorReturn(new Function<Throwable, List<Posts>>() {
                        @Override
                        public List<Posts> apply(Throwable throwable) throws Exception {
                            List<Posts> arr_posts = new ArrayList<>();
                            Posts posts = new Posts();
                            posts.setId(-1);
                            arr_posts.add(posts);
                            return arr_posts;
                        }
                    })
                    .map(new Function<List<Posts>, Resource<List<Posts>>>() {
                        @Override
                        public Resource<List<Posts>> apply(List<Posts> posts) throws Exception {
                            if (posts.size()>0)
                            {
                                if (posts.get(0).getId() == -1)
                                    return  Resource.error("some error happend",null);
                            }
                            return Resource.success(posts);
                        }
                    })
                    .subscribeOn(Schedulers.io())
            );
            Mainposts.addSource(source, new Observer<Resource<List<Posts>>>() {
                @Override
                public void onChanged(Resource<List<Posts>> listResource) {
                    Mainposts.setValue(listResource);
                    Mainposts.removeSource(source);
                }
            });
        }

        return Mainposts;
    }

}
