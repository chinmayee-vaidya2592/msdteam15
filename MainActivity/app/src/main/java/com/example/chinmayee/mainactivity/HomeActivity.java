package com.example.chinmayee.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    TabLayout tabLayout;
    private Firebase myFirebaseRef;
    private  Button button;
    private  Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://flickering-inferno-293.firebaseio.com/");
        setupTablayout();

        button = (Button) findViewById(R.id.workshop);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(HomeActivity.this,
                        MyOppsActivity.class);
                startActivity(myIntent);
            }
        });

        button1 = (Button) findViewById(R.id.talk);

        // Capture button clicks
        button1.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(HomeActivity.this,
                        OpportunityActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void setupTablayout() {

        String userId = "001722744";
        getUserFromDb(userId);
        // TODO Auto-generated method stub
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("THIS WEEK"));
        tabLayout.addTab(tabLayout.newTab().setText("RECOMMENDED"));
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
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

    private void getUserFromDb(String userId){

        //final UserObject uo = new UserObject();
        String user = "user/"+userId;
        myFirebaseRef.child("user/001722744").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("**********************************" + dataSnapshot);
                /*uo.setAbout(dataSnapshot.child("about").toString());
                uo.setEmail(dataSnapshot.child("email").toString());
                uo.setFname(dataSnapshot.child("fname").toString());
                uo.setLname(dataSnapshot.child("lname").toString());*/
                Integer lev = Integer.parseInt(dataSnapshot.child("level").getValue().toString());
                //uo.setLevel(lev);
                Integer[] dimScore = new Integer[5];
                int sumScore=0;
                for (int j = 1; j < 6; j++) {
                    dimScore[j - 1] = Integer.parseInt((String) dataSnapshot.child("dimensions").child("d" + j).getValue());
                    sumScore += dimScore[j - 1];
                }
                TextView userName = (TextView) findViewById(R.id.username);
                Button star = (Button) findViewById(R.id.star);
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBarHome);

                userName.setText(dataSnapshot.child("fname").getValue().toString().toUpperCase());
                progressBar.setProgress(sumScore);
                star.setText(((Integer) lev).toString());

                final int nextScore = sumScore;
                Firebase getLevelScore = new Firebase("https://flickering-inferno-293.firebaseio.com/level/" + lev.toString());
                getLevelScore.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String levScore = dataSnapshot.getValue().toString();
                        TextView progressScore = (TextView) findViewById(R.id.points);
                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBarHome);
                        progressBar.setMax(Integer.parseInt(levScore));
                        progressScore.setText(nextScore + "/" + levScore);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
