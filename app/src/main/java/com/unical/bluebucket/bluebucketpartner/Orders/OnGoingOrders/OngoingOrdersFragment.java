package com.unical.bluebucket.bluebucketpartner.Orders.OnGoingOrders;

import android.content.Context;
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

import com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.Model.NewOrderDetails;
import com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.RecyclerAdapter.NewOrdersAdapter;
import com.unical.bluebucket.bluebucketpartner.Orders.OnGoingOrders.Model.OnGoingOrders;
import com.unical.bluebucket.bluebucketpartner.Orders.OnGoingOrders.RecyclerAdapter.OnGoingOrdersAdapter;
import com.unical.bluebucket.bluebucketpartner.R;
import com.unical.bluebucket.bluebucketpartner.Utils.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OngoingOrdersFragment extends Fragment {
    private static final String TAG = "OngoingOrdersFragment";
    String sessionPhoneNumber,sessionBarId,sessionEmailId;
    private ApiInterface apiInterface;
    ArrayList<OnGoingOrders> onGoingOrders;
    Context mContext;
    RecyclerView recyclerOngoingOrders ;
    protected RecyclerView.LayoutManager mLayoutManager;
//  private ArrayList<DrinkDetails> drinks = new ArrayList<>();
    OnGoingOrdersAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_ongoing_orders,container,false);
        mContext = view.getContext();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        sessionPhoneNumber = preferences.getString("@string/SessionPhoneNumber","");
        sessionBarId = preferences.getString("@string/SessionBarId","");
        sessionEmailId = preferences.getString("@string/SessionEmailId","");
        Log.d(TAG, "onCreate: started with barId - "+sessionBarId);


        recyclerOngoingOrders = view.findViewById(R.id.recycler_on_going_orders);
        mLayoutManager = new LinearLayoutManager(mContext);
        recyclerOngoingOrders.setLayoutManager(mLayoutManager);

        getOnGoingOrders();



        return view;
    }

    private void getOnGoingOrders() {


        // getting API Client and make request/call to server

        apiInterface= ApiInterface.ApiClient.getApiClient().create(ApiInterface.class);

        Call<ArrayList<OnGoingOrders>> call = apiInterface.refreshOngoingOrders(sessionBarId);

        Log.d(TAG,"Function Called : RefreshNewOrders"+ call.request().url());

        call.enqueue(new Callback<ArrayList<OnGoingOrders>>() {

            @Override
            public void onResponse(Call<ArrayList<OnGoingOrders>> call, Response<ArrayList<OnGoingOrders>> response) {
                Log.d(TAG," Function Succeeded");
                onGoingOrders = response.body();

                //Log.d(TAG, "onResponse: "+newOrders.get(0).getCustomerName());
                mAdapter = new OnGoingOrdersAdapter(onGoingOrders,mContext);
                recyclerOngoingOrders.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<OnGoingOrders>> call, Throwable t) {

                Log.d(TAG,"failed - " + t.getMessage());

            }
        });










    }
}
