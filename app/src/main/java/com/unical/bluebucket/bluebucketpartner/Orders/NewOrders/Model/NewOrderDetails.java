package com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.Model;

import com.google.gson.annotations.SerializedName;

public class NewOrderDetails {


    @SerializedName("order_id")
    String orderId;

    @SerializedName("customer_id")
    String customerId;

    @SerializedName("customer_name")
    String customerName;

    @SerializedName("ac_service")
    String serviceType;

    @SerializedName("order_time")
    String orderTime;

    public String getOrderId() {
        return orderId;
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

    public String getOrderTime() {
        return orderTime;
    }
}
