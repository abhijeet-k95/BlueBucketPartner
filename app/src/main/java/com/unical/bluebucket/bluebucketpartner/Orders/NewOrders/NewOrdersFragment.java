package com.unical.bluebucket.bluebucketpartner.Orders.NewOrders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.unical.bluebucket.bluebucketpartner.Home.HomeActivity;
import com.unical.bluebucket.bluebucketpartner.LogIn.LogInActivity;
import com.unical.bluebucket.bluebucketpartner.LogIn.Model.LogInData;
import com.unical.bluebucket.bluebucketpartner.MenuCard.RecyclerAdapter.MenuCardDrinkTypeAdapter;
import com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.Model.NewOrderDetails;
import com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.RecyclerAdapter.NewOrdersAdapter;
import com.unical.bluebucket.bluebucketpartner.Orders.OnGoingOrders.RecyclerAdapter.OnGoingOrdersAdapter;
import com.unical.bluebucket.bluebucketpartner.R;
import com.unical.bluebucket.bluebucketpartner.Utils.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewOrdersFragment extends Fragment {
    private static final String TAG = "NewOrdersFragment";
    String sessionPhoneNumber,sessionBarId,sessionEmailId;

    Context mContext;
    private ApiInterface apiInterface;
    RecyclerView recyclerNewOrders ;
    protected RecyclerView.LayoutManager mLayoutManager;
    NewOrdersAdapter mAdapter;
    ImageView refreshNewOrders;
    ArrayList<NewOrderDetails> newOrders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_new_orders,container,false);
        mContext = view.getContext();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        sessionPhoneNumber = preferences.getString("@string/SessionPhoneNumber","");
        sessionBarId = preferences.getString("@string/SessionBarId","");
        sessionEmailId = preferences.getString("@string/SessionEmailId","");
        Log.d(TAG, "onCreate: started with barId - "+sessionBarId);


        recyclerNewOrders = view.findViewById(R.id.recycler_new_orders);
        refreshNewOrders = view.findViewById(R.id.img_new_orders_refresh);
        mLayoutManager = new LinearLayoutManager(mContext);
        recyclerNewOrders.setLayoutManager(mLayoutManager);

        refreshNewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getNewOrders();

            }
        });

        getNewOrders();


        return view;
    }

    private void getNewOrders() {

        // getting API Client and make request/call to server

        apiInterface= ApiInterface.ApiClient.getApiClient().create(ApiInterface.class);

        Call<ArrayList<NewOrderDetails>> call = apiInterface.refreshNewOrders(sessionBarId);

        Log.d(TAG,"Function Called : RefreshNewOrders"+ call.request().url());

        call.enqueue(new Callback<ArrayList<NewOrderDetails>>() {

            @Override
            public void onResponse(Call<ArrayList<NewOrderDetails>> call, Response<ArrayList<NewOrderDetails>> response) {
                Log.d(TAG," Function Succeeded");
                newOrders = response.body();

                //Log.d(TAG, "onResponse: "+newOrders.get(0).getCustomerName());

                mAdapter = new NewOrdersAdapter(newOrders,mContext);
                recyclerNewOrders.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<NewOrderDetails>> call, Throwable t) {

                Log.d(TAG,"failed - " + t.getMessage());

            }
        });
    }


}
