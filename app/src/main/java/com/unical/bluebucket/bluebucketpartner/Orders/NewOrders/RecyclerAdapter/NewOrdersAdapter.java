package com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.RecyclerAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.Model.NewOrderDetails;
import com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.Model.OrderAccepted;
import com.unical.bluebucket.bluebucketpartner.Orders.OnGoingOrders.RecyclerAdapter.OnGoingOrdersAdapter;
import com.unical.bluebucket.bluebucketpartner.R;
import com.unical.bluebucket.bluebucketpartner.Utils.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewOrdersAdapter extends RecyclerView.Adapter<NewOrdersAdapter.MyViewHolder> {
    private static final String TAG = "NewOrdersAdapter";
    String sessionPhoneNumber,sessionBarId,sessionEmailId;
    private ApiInterface apiInterface;

    ArrayList<NewOrderDetails> newOrders;
    private Context mContext;

    OrderAccepted oderAccept;

    public NewOrdersAdapter(ArrayList<NewOrderDetails> newOrders, Context mContext) {
        this.newOrders = newOrders;
        this.mContext = mContext;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        this.sessionPhoneNumber = preferences.getString("@string/SessionPhoneNumber","");
        this.sessionBarId = preferences.getString("@string/SessionBarId","");
        this.sessionEmailId = preferences.getString("@string/SessionEmailId","");
        Log.d(TAG, "onCreate: started with barId - "+this.sessionBarId);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.snippet_new_orders_card_view, parent, false);
        return new NewOrdersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final String orderId = newOrders.get(position).getOrderId();
        final String custId = newOrders.get(position).getCustomerId();


        holder.tvCustomerName.setText(newOrders.get(position).getCustomerName());
        holder.tvOrderTime.setText(newOrders.get(position).getOrderTime());
        holder.tvService.setText(newOrders.get(position).getServiceType());

        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Order Accepted - "+newOrders.get(position).getOrderId());

                // getting API Client and make request/call to server

                apiInterface= ApiInterface.ApiClient.getApiClient().create(ApiInterface.class);

                Call<OrderAccepted> call = apiInterface.orderAcceptedCall(orderId,sessionBarId,custId);

                Log.d(TAG,"Function Called : RefreshNewOrders"+ call.request().url());

                call.enqueue(new Callback<OrderAccepted>() {

                    @Override
                    public void onResponse(Call<OrderAccepted> call, Response<OrderAccepted> response) {
                        Log.d(TAG," Function Succeeded");

                        oderAccept = response.body();
                        if(oderAccept.isSuccess()){

                            Toast.makeText(mContext, "Order Accepted !",
                            Toast.LENGTH_LONG).show();
                            holder.rlOrderAccepted.setVisibility(View.GONE);


                        }
                        else{

                            Toast.makeText(mContext, "Order Not yet Accepted - "+oderAccept.getErrorMessage(),
                                    Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<OrderAccepted> call, Throwable t) {

                        Log.d(TAG,"failed - " + t.getMessage());
                        Toast.makeText(mContext, "Check Internet Connection - "+t.getMessage(),
                                Toast.LENGTH_LONG).show();


                    }
                });
            }
        });

        holder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Order Rejected - "+newOrders.get(position).getOrderId());

                // getting API Client and make request/call to server

                apiInterface= ApiInterface.ApiClient.getApiClient().create(ApiInterface.class);

                Call<OrderAccepted> call = apiInterface.orderRejectedCall(orderId);

                Log.d(TAG,"Function Called : RefreshNewOrders"+ call.request().url());

                call.enqueue(new Callback<OrderAccepted>() {

                    @Override
                    public void onResponse(Call<OrderAccepted> call, Response<OrderAccepted> response) {
                        Log.d(TAG," Function Succeeded");

                        oderAccept = response.body();
                        if(oderAccept.isSuccess()){

                            Toast.makeText(mContext, "Order Rejected !",
                                    Toast.LENGTH_LONG).show();
                            holder.rlOrderAccepted.setVisibility(View.GONE);


                        }
                        else{

                            Toast.makeText(mContext, "Order Not yet Rejected - "+oderAccept.getErrorMessage(),
                                    Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<OrderAccepted> call, Throwable t) {

                        Log.d(TAG,"failed - " + t.getMessage());
                        Toast.makeText(mContext, "Check Internet Connection - "+t.getMessage(),
                                Toast.LENGTH_LONG).show();


                    }
                });
            }
        });



    }

    @Override
    public int getItemCount() {
        return newOrders.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvCustomerName, tvOrderTime,tvService;
        Button btnAccept, btnReject;
        RelativeLayout rlOrderAccepted;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvCustomerName = itemView.findViewById(R.id.new_order_customer_name);
            tvOrderTime = itemView.findViewById(R.id.new_order_time);
            tvService = itemView.findViewById(R.id.new_order_service);
            btnAccept = itemView.findViewById(R.id.btn_new_order_accept);
            btnReject = itemView.findViewById(R.id.btn_new_order_reject);
            rlOrderAccepted = itemView.findViewById(R.id.rl_order_accepted);
        }
    }
}
