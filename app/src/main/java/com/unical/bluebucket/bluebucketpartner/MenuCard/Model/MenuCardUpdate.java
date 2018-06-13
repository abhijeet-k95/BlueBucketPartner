package com.unical.bluebucket.bluebucketpartner.MenuCard.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuCardUpdate {

    @SerializedName("success")
    @Expose
    boolean success;


    @SerializedName("error_code")
    @Expose
    String errorCode;

    public boolean isSuccess() {
        return success;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
