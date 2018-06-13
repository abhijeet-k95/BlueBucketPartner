package com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.Model;

import com.google.gson.annotations.SerializedName;

public class OrderAccepted {

    @SerializedName("success")
    boolean success;

    @SerializedName("error_code")
    String errorCode;

    @SerializedName("error_message")
    String errorMessage;

    public boolean isSuccess() {
        return success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
