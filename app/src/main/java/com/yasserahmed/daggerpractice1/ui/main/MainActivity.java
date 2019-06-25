package com.yasserahmed.daggerpractice1.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.yasserahmed.daggerpractice1.BaseActivity;
import com.yasserahmed.daggerpractice1.R;
import com.yasserahmed.daggerpractice1.ui.main.posts.PostsFragment;
import com.yasserahmed.daggerpractice1.ui.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void SetFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container,new PostsFragment())
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_profile:{
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container,new ProfileFragment())
                        .commit();
                break;
            }
            case R.id.nav_post:{
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container,new PostsFragment())
                        .commit();
                break;
            }
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
