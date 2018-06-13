package com.unical.bluebucket.bluebucketpartner.LogIn.Model;

import com.google.gson.annotations.SerializedName;

public class LogInData {


    @SerializedName("success")
    boolean  success;

    @SerializedName("error_code")
    String errorCode;

    @SerializedName("error_message")
    String errorMessage;

    @SerializedName("bar_id")
    String  barId;

    @SerializedName("email_id")
    String  emailId;

    public boolean isSuccess() {
        return success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getBarId() {
        return barId;
    }

    public String isEmailId() {
        return emailId;
    }
}
