package com.example.chinmayee.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    TabLayout tabLayout;
    private Firebase myFirebaseRef;
    private SearchView searchView;
    private  Button buttonWorkshop;
    private  Button buttonTalk;
    private  Button buttonCoop;
    private  Button buttonVolunteer;
    private  Button buttonHackAThon;
    private  Button buttonClubMem;
    private ProgressBar progressBar;
    private String userLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://flickering-inferno-293.firebaseio.com/");
        setupTablayout();
        setupSearch();
        setupButtonsAction();
    }

    private void setupButtonsAction(){
        buttonWorkshop = (Button) findViewById(R.id.workshop);
        buttonWorkshop.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(HomeActivity.this, OpportunityActivity.class);
                Bundle b = new Bundle();
                b.putString("filter", "workshop"); //Your id
                b.putString("userLevel", userLevel);
                myIntent.putExtras(b); //Put your id to your next Intent
                startActivity(myIntent);
            }
        });

        buttonTalk = (Button) findViewById(R.id.talk);
        buttonTalk.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(HomeActivity.this, OpportunityActivity.class);
                Bundle b = new Bundle();
                b.putString("filter", "talk"); //Your id
                b.putString("userLevel", userLevel);
                myIntent.putExtras(b); //Put your id to your next Intent
                startActivity(myIntent);
            }
        });

        buttonCoop = (Button) findViewById(R.id.coop);
        buttonCoop.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(HomeActivity.this,OpportunityActivity.class);
                Bundle b = new Bundle();
                b.putString("filter", "coop"); //Your id
                b.putString("userLevel", userLevel);
                myIntent.putExtras(b); //Put your id to your next Intent
                startActivity(myIntent);
            }
        });

        buttonVolunteer = (Button) findViewById(R.id.volunteering);
        buttonVolunteer.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(HomeActivity.this,OpportunityActivity.class);
                Bundle b = new Bundle();
                b.putString("filter", "volunteering"); //Your id
                b.putString("userLevel", userLevel);
                myIntent.putExtras(b); //Put your id to your next Intent
                startActivity(myIntent);
            }
        });

        buttonClubMem = (Button) findViewById(R.id.club_membership);
        buttonClubMem.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(HomeActivity.this, OpportunityActivity.class);
                Bundle b = new Bundle();
                b.putString("filter", "club_membership"); //Your id
                b.putString("userLevel", userLevel);
                myIntent.putExtras(b); //Put your id to your next Intent
                startActivity(myIntent);
            }
        });

        buttonHackAThon = (Button) findViewById(R.id.hack_a_thon);
        buttonHackAThon.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(HomeActivity.this, OpportunityActivity.class);
                Bundle b = new Bundle();
                b.putString("filter", "hack-a-thon"); //Your id
                b.putString("userLevel", userLevel);
                myIntent.putExtras(b); //Put your id to your next Intent
                startActivity(myIntent);
            }
        });
    }

    private void setupSearch(){
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search Catagory");

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            }
        });

        //*** setOnQueryTextListener ***
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    searchView = (SearchView) findViewById(R.id.searchView);
                    searchView.setIconified(true);
                }
                Intent myIntent = new Intent(HomeActivity.this,OpportunityActivity.class);
                Bundle b = new Bundle();
                b.putString("filter", query); //Your id
                b.putString("userLevel", userLevel);
                myIntent.putExtras(b); //Put your id to your next Intent
                startActivity(myIntent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setupTablayout() {

        String userId = "001722744";
        getUserFromDb(userId);
        Intent intent = getIntent();

        Bundle b = new Bundle();
        b.putString("filter", ""); //Your id
        b.putString("userLevel", userLevel);
        intent.putExtras(b);

        // TODO Auto-generated method stub
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("THIS WEEK"));
        tabLayout.addTab(tabLayout.newTab().setText("RECOMMENDED"));
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), intent.getExtras());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void getUserFromDb(String userId){
        String user = "user/"+userId;
        //"user/001722744"
        myFirebaseRef.child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("**********************************" + dataSnapshot);
                Integer lev = Integer.parseInt(dataSnapshot.child("level").getValue().toString());
                userLevel = lev.toString();
                Integer[] dimScore = new Integer[5];
                int sumScore=0;
                for (int j = 1; j < 6; j++) {
                    dimScore[j - 1] = Integer.parseInt((String) dataSnapshot.child("dimensions").child("d" + j).getValue());
                    sumScore += dimScore[j - 1];
                }
                TextView userName = (TextView) findViewById(R.id.username);
                Button star = (Button) findViewById(R.id.star);

                userName.setText(dataSnapshot.child("fname").getValue().toString().toUpperCase());
                star.setText(((Integer) lev).toString());

                final int nextScore = sumScore;
                Firebase getLevelScore = new Firebase("https://flickering-inferno-293.firebaseio.com/level/" + lev.toString());
                getLevelScore.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String levScore = dataSnapshot.getValue().toString();
                        TextView progressScore = (TextView) findViewById(R.id.points);
                        progressBar = (ProgressBar) findViewById(R.id.progressBarHome);
                        progressBar.setMax(Integer.parseInt(levScore));
                        progressBar.setProgress(nextScore);
                        progressScore.setText(nextScore + "/" + levScore + " Pts");
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {}
                });
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {}
        });
    }

}
