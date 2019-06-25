package com.yasserahmed.daggerpractice1.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yasserahmed.daggerpractice1.BaseActivity;
import com.yasserahmed.daggerpractice1.R;
import com.yasserahmed.daggerpractice1.ui.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this,"Hi Main Activity",Toast.LENGTH_SHORT).show();
       TestFragment();
    }

    private void TestFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container,new ProfileFragment())
                .commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logut_item:
                sessionManager.loguot();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
