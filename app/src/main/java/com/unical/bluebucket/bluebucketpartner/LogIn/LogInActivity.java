package com.unical.bluebucket.bluebucketpartner.LogIn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.unical.bluebucket.bluebucketpartner.Home.HomeActivity;
import com.unical.bluebucket.bluebucketpartner.LogIn.Model.LogInData;
import com.unical.bluebucket.bluebucketpartner.MainActivity;
import com.unical.bluebucket.bluebucketpartner.R;
import com.unical.bluebucket.bluebucketpartner.Utils.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {

    private static final String TAG = "LogInActivity";

    private ApiInterface apiInterface;

    EditText mPhoneNumber,mPasword;
    Button mLogIn;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);



        mPhoneNumber = findViewById(R.id.logInPhoneNumber);
        mPasword = findViewById(R.id.logInPassword);
        mLogIn= findViewById(R.id.btn_login);
        mProgressBar = findViewById(R.id.logInRequestProgressBar);
        mProgressBar.setVisibility(View.GONE);

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Attempting Login");

                String phonenumber = mPhoneNumber.getText().toString();
                String password = mPasword.getText().toString();
                if(!phonenumber.equals("")&& !password.equals("")){
                    mProgressBar.setVisibility(View.VISIBLE);

                    login(phonenumber,password);
                }


            }
        });

    }

    private void login(final String phonenumber, final String password) {


        // getting API Client and make request/call to server
        apiInterface= ApiInterface.ApiClient.getApiClient().create(ApiInterface.class);

        Call<LogInData> call = apiInterface.checkLogin(phonenumber,password);

        Log.d(TAG,"Function Called");

        call.enqueue(new Callback<LogInData>() {

            @Override
            public void onResponse(Call<LogInData> call, Response<LogInData> response) {
                Log.d(TAG," Function Succeeded");

                if(response.body().isSuccess()){

                    Log.d(TAG," Log In Successful ");
                    Toast.makeText(LogInActivity.this, "Log In Successful!",
                            Toast.LENGTH_LONG).show();
                    setSessionParams(phonenumber,password,response.body().getBarId(),response.body().isEmailId());
                    mProgressBar.setVisibility(View.GONE);
                    //getSessioParams();

                    Intent intent =new Intent(LogInActivity.this,HomeActivity.class);
                    startActivity(intent);

                }
                else {

                    Log.d(TAG,"Log in unsuccessful check password and phone number ");
                    mProgressBar.setVisibility(View.GONE);
                    Toast.makeText(LogInActivity.this, "Log In usnuccessful! - "+response.body().getErrorMessage(),
                            Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<LogInData> call, Throwable t) {

                Log.d(TAG,"failed - " + t.getMessage());
                Toast.makeText(LogInActivity.this, "Log In Failed!",
                        Toast.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.GONE);

            }
        });




    }

    private void setSessionParams(String phonenumber, String password, String barId, String emailId){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LogInActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        Log.d(TAG, "setSessionParams: Saving Session Parameter \nusername: "+phonenumber+
                "\ncookie: "+password);
        editor.putString("@string/SessionPhoneNumber",phonenumber);
        editor.commit();
        editor.putString("@string/SessionBarId",barId);
        editor.commit();
        editor.putString("@string/SessionEmailId",emailId);
        editor.commit();
        editor.putString("@string/SessionPassword",password);
        editor.commit();

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
