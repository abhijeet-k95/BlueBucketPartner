package com.unical.bluebucket.bluebucketpartner.Orders.OnGoingOrders.Model;

import com.google.gson.annotations.SerializedName;

public class OnGoingOrders {

    @SerializedName("order_id")
    String orderid;


    @SerializedName("customer_id")
    String customerId;


    @SerializedName("customer_name")
    String customerName;

    @SerializedName("ac_service")
    String serviceType;


    @SerializedName("order_placed_time")
    String orderPlacedTime;

    @SerializedName("current_time")
    String currentTime;


    public String getOrderid() {
        return orderid;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }


    public String getServiceType() {
        return serviceType;
    }

    public String getOrderPlacedTime() {
        return orderPlacedTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }
}
