package com.unical.bluebucket.bluebucketpartner.Account;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.unical.bluebucket.bluebucketpartner.Account.Model.AccountDetails;
import com.unical.bluebucket.bluebucketpartner.R;
import com.unical.bluebucket.bluebucketpartner.Utils.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    private static final String TAG = "AccountActivity";
    private Context mContext = this;
    String sessionPhoneNumber,sessionBarId,sessionEmailId;
    private AccountDetails details;
    private ApiInterface apiInterface;


    private TextView name, phone,email,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        sessionPhoneNumber = preferences.getString("@string/SessionPhoneNumber","");
        sessionBarId = preferences.getString("@string/SessionBarId","");
        sessionEmailId = preferences.getString("@string/SessionEmailId","");
        Log.d(TAG, "onCreate: started with barId - "+sessionBarId);

        name = findViewById(R.id.tv_name);
        phone = findViewById(R.id.tv_phn);
        email= findViewById(R.id.tv_email);
        address = findViewById(R.id.account_address);

        getAccountDetails();





    }

    private void getAccountDetails() {

        // getting API Client and make request/call to server
        apiInterface= ApiInterface.ApiClient.getApiClient().create(ApiInterface.class);

        Call<AccountDetails> call = apiInterface.getAccountDetails(sessionBarId);

        Log.d(TAG,"Function Called");

        call.enqueue(new Callback<AccountDetails>() {

            @Override
            public void onResponse(Call<AccountDetails> call, Response<AccountDetails> response) {
                Log.d(TAG,"Succeeded");



                details = response.body();

                name.setText(details.getName());
                phone.setText(details.getPhoneNumber());
                email.setText(details.getEmail());
                address.setText(details.getAddress());




            }

            @Override
            public void onFailure(Call<AccountDetails> call, Throwable t) {

                Log.d(TAG,"failed");

            }
        });
    }


}
