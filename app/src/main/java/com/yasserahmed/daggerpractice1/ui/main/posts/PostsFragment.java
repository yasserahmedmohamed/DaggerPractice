package com.yasserahmed.daggerpractice1.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yasserahmed.daggerpractice1.Models.Posts;
import com.yasserahmed.daggerpractice1.R;
import com.yasserahmed.daggerpractice1.ViewModels.ViewModelProviderFactory;
import com.yasserahmed.daggerpractice1.adapters.PostsRecyclerAdapter;
import com.yasserahmed.daggerpractice1.ui.main.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private static final String TAG = "PostsFragment";
    private PostViewModel postViewModel;
    private RecyclerView recyclerView;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    PostsRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.recycler_view);
        postViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(PostViewModel.class);

        GetPostsObserve();
        initRecycleView();
    }
    private  void GetPostsObserve(){
        postViewModel.ObservePosts().removeObservers(getViewLifecycleOwner());
        postViewModel.ObservePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Posts>>>() {
            @Override
            public void onChanged(Resource<List<Posts>> listResource) {
                if (listResource != null)
                {
                    switch (listResource.status)
                    {
                        case LOADING:
                        {
                            break;
                        }
                        case ERROR:
                        {
                            Log.d(TAG,"error in load posts : "+listResource.message);
                            break;
                        }
                        case SUCCESS:
                        {
                            adapter.setPosts(listResource.data);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initRecycleView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

    }
}
