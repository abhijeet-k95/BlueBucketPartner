package com.unical.bluebucket.bluebucketpartner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.unical.bluebucket.bluebucketpartner.Home.HomeActivity;
import com.unical.bluebucket.bluebucketpartner.LogIn.LogInActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String phone = preferences.getString("@string/SessionPhoneNumber","");

        Log.d(TAG, "onCreate: "+phone);

        if(phone.equals("")){
            //Intent intentLogin = new Intent(this, LogInActivity.class);
            //this.startActivity(intentLogin);

            Intent intent =new Intent(MainActivity.this,LogInActivity.class);
            startActivity(intent);

        }
        else{

            Intent intent =new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);

        }
    }
}
