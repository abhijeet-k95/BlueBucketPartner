package com.unical.bluebucket.bluebucketpartner.Utils;



import com.unical.bluebucket.bluebucketpartner.Account.Model.AccountDetails;
import com.unical.bluebucket.bluebucketpartner.LogIn.Model.LogInData;
import com.unical.bluebucket.bluebucketpartner.MenuCard.Model.DrinkDetails;
import com.unical.bluebucket.bluebucketpartner.MenuCard.Model.MenuCardDetails;
import com.unical.bluebucket.bluebucketpartner.MenuCard.Model.MenuCardUpdate;
import com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.Model.NewOrderDetails;
import com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.Model.OrderAccepted;
import com.unical.bluebucket.bluebucketpartner.Orders.OnGoingOrders.Model.OnGoingOrders;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by admin on 11/10/2017.
 */

public interface ApiInterface {


    @FormUrlEncoded
    @POST("check_login_partner")
    Call<LogInData> checkLogin(@Field("phone_number") String phoneNumber, @Field("password") String password);

    @FormUrlEncoded
    @POST("get_menu_card")
    Call<ArrayList<MenuCardDetails>> getDrinks(@Field("store_id") String storeId);



    @GET("get_all_drinks")
    Call<ArrayList<DrinkDetails>> getAllDrinkDetails();

    @FormUrlEncoded
    @POST("update_menu_card")
    Call<MenuCardUpdate> updateMenuCard(@Field("insert_or_update") String insert_or_update,
                                        @Field("store_id") String store_id,
                                        @Field("drink_id") String drink_id,
                                        @Field("ac_service") String ac_service,
                                        @Field("rate_30_ml") String rate_30_ml,
                                        @Field("rate_60_ml") String rate_60_ml,
                                        @Field("rate_90_ml") String rate_90_ml,
                                        @Field("rate_120_ml") String rate_120_ml,
                                        @Field("rate_150_ml") String rate_150_ml,
                                        @Field("rate_180_ml") String rate_180_ml);



    @GET("refresh_new_orders/{store_id}")
    Call<ArrayList<NewOrderDetails>> refreshNewOrders(@Path("store_id") String store_id);


    @GET("refresh_ongoing_orders/{store_id}")
    Call<ArrayList<OnGoingOrders>> refreshOngoingOrders(@Path("store_id") String store_id);

    @FormUrlEncoded
    @POST("bar_details")
    Call<AccountDetails> getAccountDetails(@Field("store_id") String storeId);

    @GET("order_accepted_call/{order_id}/{store_id}/{cust_id}")
    Call<OrderAccepted> orderAcceptedCall(@Path("order_id") String order_id,@Path("store_id") String store_id,@Path("cust_id") String cust_id);

    @GET("order_rejected_call/{order_id}")
    Call<OrderAccepted> orderRejectedCall(@Path("order_id") String order_id);



    public class ApiClient {
        public static  final String BASE_URL="http://192.168.1.103/bluebucket/android/public/";

        public static Retrofit retrofit =null;

        public  static Retrofit getApiClient(){
            if(retrofit==null){
                retrofit = new Retrofit.Builder().
                        baseUrl(BASE_URL).
                        addConverterFactory(GsonConverterFactory.create()).
                        build();
            }
            return retrofit;
        }

    }



}

