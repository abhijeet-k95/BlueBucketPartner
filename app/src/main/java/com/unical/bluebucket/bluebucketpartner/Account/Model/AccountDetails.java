package com.unical.bluebucket.bluebucketpartner.Account.Model;

import com.google.gson.annotations.SerializedName;

public class AccountDetails {

    @SerializedName("store_id")
    private String StoreId;

    @SerializedName("store_name")
    private String Name;

    @SerializedName("email_id")
    private String Email;

    @SerializedName("mobile_number")
    private String PhoneNumber;


    @SerializedName("address")
    private String Address;

    @SerializedName("certificate")
    private String Certi;

    public String getStoreId() {
        return StoreId;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public String getCerti() {
        return Certi;
    }
}
