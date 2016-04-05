package com.example.chinmayee.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    MyApplication myapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    //    Intent myIntent = new Intent(Login.this, HomeActivity.class);
    //    Bundle b = new Bundle();
    //    b.putString("filter", query); //Your id
    //    b.putString("userLevel", userLevel);
    //    myIntent.putExtras(b); //Put your id to your next Intent
    //    startActivity(myIntent);

        final EditText edUsername  = (EditText)findViewById(R.id.edit_username);
        final EditText  edPassword  = (EditText)findViewById(R.id.edit_password);
        Button btnValidate = (Button)findViewById(R.id.loginButton);
        btnValidate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String uname = edUsername.getText().toString();
                String pass = edPassword.getText().toString();
                if (uname.equals("demo") && pass.equals("demo1")) {
                    Intent intent = new Intent(Login.this, HomeActivity.class);
                  //  intent.putExtra("username", edUsername.getText().toString());
                    myapp = (MyApplication) getApplication();
                    myapp.setUserId("001722744");
                    myapp.setUserEmail(uname);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Invalid Usename password pair.- Username- "+uname+"Pwd- "+pass, Toast.LENGTH_LONG).show();
                }
            }
        });



    }

}
