package com.unical.bluebucket.bluebucketpartner.Orders.OnGoingOrders.RecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unical.bluebucket.bluebucketpartner.Orders.OnGoingOrders.Model.OnGoingOrders;
import com.unical.bluebucket.bluebucketpartner.R;

import java.util.ArrayList;

public class OnGoingOrdersAdapter extends RecyclerView.Adapter<OnGoingOrdersAdapter.MyViewHolder> {

    ArrayList<OnGoingOrders> onGoingOrders;
    Context mContext;

    public OnGoingOrdersAdapter(ArrayList<OnGoingOrders> onGoingOrders, Context mContext) {
        this.onGoingOrders = onGoingOrders;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.snippet_ongoing_orders_card_view, parent, false);
        return new OnGoingOrdersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvCustomerName.setText(onGoingOrders.get(position).getCustomerName());
        holder.tvTotalTime.setText(onGoingOrders.get(position).getOrderPlacedTime());
        if (onGoingOrders.get(position).getServiceType().equals("0")){
            holder.tvServiceType.setText("NON - AC");
        }
        else {
            holder.tvServiceType.setText("AC");
        }
    }

    @Override
    public int getItemCount() {
        return onGoingOrders.size();
    }

        public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvCustomerName, tvTotalTime, tvCurrentBill, tvServiceType;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvCustomerName = itemView.findViewById(R.id.ongoing_orders_customer_name);
            tvTotalTime = itemView.findViewById(R.id.ongoing_orders_total_time);
            tvCurrentBill = itemView.findViewById(R.id.ongoing_orders_current_bill);
            tvServiceType = itemView.findViewById(R.id.ongoing_orders_service_type);
        }
    }
}
