package com.example.chinmayee.mainactivity;

//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class OppDetail extends AppCompatActivity {
    ImageView imageView1;
    RoundImage roundedImage;
    TextView mText1;
    TextView mText2;
    TextView mText3;
    TextView mText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opp_detail);
        Firebase.setAndroidContext(this);
        Firebase mFBRef = new Firebase("https://flickering-inferno-293.firebaseio.com/");

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        //Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.profimg2);
        //roundedImage = new RoundImage(bm);
        imageView1.setImageDrawable(roundedImage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mText1 = (TextView) findViewById(R.id.oppDate);
        mText2 = (TextView) findViewById(R.id.oppName);
        mText3 = (TextView) findViewById(R.id.oppDes);
        mText4 = (TextView) findViewById(R.id.oppLoc);

        // Attach an listener to read the data at our posts reference
        mFBRef.child("opportunity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    // NO Event image

                    // Event date
                    mText1.setText((String) postSnapshot.child("date").getValue());

                    // Event name
                    mText2.setText((String) postSnapshot.child("name").getValue());

                    // Event description. diff btw short desc and long desc
                    mText3.setText((String) postSnapshot.child("shortDesc").getValue());

                    // Event loc
                    mText4.setText((String) postSnapshot.child("location").getValue());

                    // Missing contact info and attendees in db

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }

    private void addMapFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MapFragment fragment = new MapFragment();
        transaction.add(R.id.mapView, fragment);
        transaction.commit();
    }



}
