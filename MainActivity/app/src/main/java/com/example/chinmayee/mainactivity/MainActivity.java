package com.example.chinmayee.mainactivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    RoundImage roundedImage;
    TextView mText1;
    TextView mText2;
    TextView mText3;
    TextView mText4;
    ProgressBar mBar0;
    ProgressBar mBar1;
    ProgressBar mBar2;
    ProgressBar mBar3;
    ProgressBar mBar4;
    ProgressBar mBar5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        Firebase mFBRef = new Firebase("https://flickering-inferno-293.firebaseio.com/");

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.profimg2);
        roundedImage = new RoundImage(bm);
        imageView1.setImageDrawable(roundedImage);
        mText1 = (TextView) findViewById(R.id.userName);
        mText2 = (TextView) findViewById(R.id.bio);
        mText3 = (TextView) findViewById(R.id.levelNum);
        mText4 = (TextView) findViewById(R.id.levelScore);
        mBar0 = (ProgressBar) findViewById(R.id.progressBar);
        mBar1 = (ProgressBar) findViewById(R.id.d1);
        mBar2 = (ProgressBar) findViewById(R.id.d2);
        mBar3 = (ProgressBar) findViewById(R.id.d3);
        mBar4 = (ProgressBar) findViewById(R.id.d4);
        mBar5 = (ProgressBar) findViewById(R.id.d5);

        // Attach an listener to read the data at our posts reference
        mFBRef.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    // Missing image

                    // Set user name
                    String name = (String) postSnapshot.child("fname").getValue() + " " +
                            (String) postSnapshot.child("lname").getValue();
                    mText1.setText(name);

                    // Set user BIO
                    //String bio = (String) postSnapshot.child("about").getValue();
                    mText2.setText((String) postSnapshot.child("about").getValue());

                    // Set user level
                    String level = "LEVEL " + (String) postSnapshot.child("level").getValue();
                    mText3.setText(level);

                    // Set Total progress bar and score
                    int[] dimScore = new int[5];
                    int sumScore=0;
                    for (int j=1; j<6; j++) {
                        dimScore[j - 1] = Integer.parseInt((String) postSnapshot.child("dimensions").child("d" + j).getValue());
                        sumScore += dimScore[j - 1];
                    }
                    mBar0.setProgress(sumScore);
                    mText4.setText(sumScore + "/1000");

                    // Update 5 progress bars
                    mBar1.setProgress(Integer.parseInt((String) postSnapshot.child("dimensions").child("d1").getValue()));
                    mBar2.setProgress(Integer.parseInt((String) postSnapshot.child("dimensions").child("d2").getValue()));
                    mBar3.setProgress(Integer.parseInt((String) postSnapshot.child("dimensions").child("d3").getValue()));
                    mBar4.setProgress(Integer.parseInt((String) postSnapshot.child("dimensions").child("d4").getValue()));
                    mBar5.setProgress(Integer.parseInt((String) postSnapshot.child("dimensions").child("d5").getValue()));

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
