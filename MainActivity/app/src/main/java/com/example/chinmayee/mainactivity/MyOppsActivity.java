package com.example.chinmayee.mainactivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.firebase.client.Firebase;

;

public class MyOppsActivity extends AppCompatActivity {
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunities);
        Firebase.setAndroidContext(this);
        setupToolbar();
        setupTablayout();
    }

    private void setupToolbar() {
        // TODO Auto-generated method stub
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarsdfs);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);}
    }

    private void setupTablayout() {
        // TODO Auto-generated method stub
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.addTab(tabLayout.newTab().setText("INPROGRESS"));
        tabLayout.addTab(tabLayout.newTab().setText(" UPCOMING "));
        tabLayout.addTab(tabLayout.newTab().setText("COMPLETED "));
        tabLayout.addTab(tabLayout.newTab().setText("  SAVED"));
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final MyOppPagerAdapter adapter = new MyOppPagerAdapter(getSupportFragmentManager(),
                                                                    tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}