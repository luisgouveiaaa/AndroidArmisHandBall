package com.example.debs.androidarmishandball.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.debs.androidarmishandball.R;

public class MainActivity extends FragmentActivity {

    private TextView mTextMessage;
    SearchFragment search = new SearchFragment();
    HomeFragment home = new HomeFragment();
    FavoritesFragment favorites = new FavoritesFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.app.FragmentManager manager = getFragmentManager();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    manager.beginTransaction().replace(R.id.fragments_container, home).commit();
                    return true;
                case R.id.navigation_favorites:
                    mTextMessage.setText(R.string.title_favorites);
                    manager.beginTransaction().replace(R.id.fragments_container, favorites).commit();
                    return true;
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.title_search);
                    manager.beginTransaction().replace(R.id.fragments_container, search).commit();
                    return true;
                default:
                    mTextMessage.setText(R.string.title_home);
                    manager.beginTransaction().replace(R.id.fragments_container, home).commit();
                    return true;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.main_title);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

}
