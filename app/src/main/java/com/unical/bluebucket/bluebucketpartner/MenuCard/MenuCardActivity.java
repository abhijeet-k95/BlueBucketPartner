package com.unical.bluebucket.bluebucketpartner.MenuCard;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.unical.bluebucket.bluebucketpartner.Home.HomeActivity;
import com.unical.bluebucket.bluebucketpartner.LogIn.LogInActivity;
import com.unical.bluebucket.bluebucketpartner.LogIn.Model.LogInData;
import com.unical.bluebucket.bluebucketpartner.MainActivity;
import com.unical.bluebucket.bluebucketpartner.MenuCard.Model.DrinkDetails;
import com.unical.bluebucket.bluebucketpartner.MenuCard.Model.MenuCardDetails;
import com.unical.bluebucket.bluebucketpartner.MenuCard.Model.MenuCardUpdate;
import com.unical.bluebucket.bluebucketpartner.MenuCard.RecyclerAdapter.MenuCardDrinkTypeAdapter;
import com.unical.bluebucket.bluebucketpartner.R;
import com.unical.bluebucket.bluebucketpartner.Utils.ApiInterface;
import com.unical.bluebucket.bluebucketpartner.Utils.ToolBarSetUp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuCardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "MenuCardActivity";
    String sessionBarId,sessionPhone;

    RecyclerView rcyclerDrinkType ;

    Button btnEditMenuCard;
    Button btnAddDrink;
    Spinner spinnerDrinkType,spinnerDrinks;
    EditText rate30ml,rate60ml,rate90ml,rate120ml,rate150ml,rate180ml;

    String barName,barAddress;
    private ApiInterface apiInterface;
    protected RecyclerView.LayoutManager mLayoutManager;
    MenuCardDrinkTypeAdapter mAdapter;
    private ArrayList<MenuCardDetails> drinks = new ArrayList<>();
    Context mContext;
    Toolbar toolbar ;

    String []allDrinksId;
    String []uniqueDrinkTypes;
    HashMap<String, ArrayList<DrinkDetails>> drinkOfDrinkTypes = new HashMap<>();


    private ArrayList<DrinkDetails> allDrinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_card);
        btnEditMenuCard = findViewById(R.id.btn_edit_menu_card);
        boolean getSeesion = getSessionParams();

       // Log.d(TAG, "onCreate: session bar id - "+sessionBarId);



        setToolBar();
        getallDrinks();




        mContext =this;
        rcyclerDrinkType = findViewById(R.id.recycler_menu_card_drink_type);
        mLayoutManager = new LinearLayoutManager(mContext);
        rcyclerDrinkType.setLayoutManager(mLayoutManager);
        getMenuCard();


        btnEditMenuCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View mView = getLayoutInflater().inflate(R.layout.snippet_dialog_edit_menu_card,null);
                spinnerDrinkType = mView.findViewById(R.id.spinner_drink_type);
                spinnerDrinks = mView.findViewById(R.id.spinner_drinks);
                btnAddDrink = mView.findViewById(R.id.btn_add_drink);
                rate30ml = mView.findViewById(R.id.rate30ml);
                rate60ml = mView.findViewById(R.id.rate60ml);
                rate90ml = mView.findViewById(R.id.rate90ml);
                rate120ml = mView.findViewById(R.id.rate120ml);
                rate150ml = mView.findViewById(R.id.rate150ml);
                rate180ml = mView.findViewById(R.id.rate180ml);

                setSpinnerDrinkType(mView, spinnerDrinkType);
                final Dialog dialog = new Dialog(MenuCardActivity.this);
                dialog.setTitle("Dialog");
                dialog.setContentView(mView);
                int width =(int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.6);
                dialog.getWindow().setLayout(width,height);
                btnAddDrink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String acService ="0";
                        String selectedDrinkType = spinnerDrinkType.getSelectedItem().toString();
                        String selectedDrink = spinnerDrinks.getSelectedItem().toString();
                        String enteredRate30ml = rate30ml.getText().toString();
                        String enteredRate60ml = rate60ml.getText().toString();
                        String enteredRate90ml = rate90ml.getText().toString();
                        String enteredRate120ml = rate120ml.getText().toString();
                        String enteredRate150ml = rate150ml.getText().toString();
                        String enteredRate180ml = rate180ml.getText().toString();
                        String selectedDrinkId = getDrinkId(selectedDrinkType,selectedDrink);
                        String insertOrUpdate = "1";

                        if(!enteredRate30ml.equals("") && !enteredRate60ml.equals("") && !enteredRate90ml.equals("") && !enteredRate120ml.equals("") && !enteredRate150ml.equals("") && !enteredRate180ml.equals("")){

                            if( !Arrays.asList(allDrinksId).contains(selectedDrinkId)){
                                Toast.makeText(view.getContext(), "New drink" +selectedDrinkType +" - "+selectedDrink , Toast.LENGTH_LONG).show();
                                insertOrUpdate="0";
                            }



                            // getting API Client and make request/call to server
                            apiInterface= ApiInterface.ApiClient.getApiClient().create(ApiInterface.class);

                            Call<MenuCardUpdate> call = apiInterface.updateMenuCard(insertOrUpdate,
                                    sessionBarId,selectedDrinkId,acService,
                                    enteredRate30ml,enteredRate60ml,enteredRate90ml,
                                    enteredRate120ml,enteredRate150ml,enteredRate180ml);

                            Log.d(TAG,"Function Called");

                            call.enqueue(new Callback<MenuCardUpdate>() {

                                @Override
                                public void onResponse(Call<MenuCardUpdate> call, Response<MenuCardUpdate> response) {
                                    Log.d(TAG," Function Succeeded");

                                    Log.d(TAG, "onResponse: Error Code - "+ response.body().getErrorCode());
                                    if(response.body().isSuccess()){

                                        Toast.makeText(MenuCardActivity.this, "Menu Card Update Successful!",
                                                Toast.LENGTH_LONG).show();

                                    }



                                }

                                @Override
                                public void onFailure(Call<MenuCardUpdate> call, Throwable t) {

                                    Log.d(TAG,"failed - " + t.getMessage());


                                }
                            });




                         //   Toast.makeText(view.getContext(), "Drink Added " +selectedDrinkType +" - "+selectedDrink , Toast.LENGTH_LONG).show();
                        }
                        else {

                            Toast.makeText(view.getContext(), "Please Enter all rates ", Toast.LENGTH_LONG).show();

                        }
                    }
                });

                dialog.show();
            }
        });




    }

    private String getDrinkId(String selectedDrinkType, String selectedDrink) {
        String drinkId = "";

        for (int i = 0; i < allDrinks.size(); i++) {

            if (selectedDrinkType.equals(allDrinks.get(i).getDrinkType()) && selectedDrink.equals(allDrinks.get(i).getDrinkName())) {
                drinkId = allDrinks.get(i).getDrinkId();
                break;
            }

        }
        return drinkId;
    }


    private void getallDrinks() {


        // getting API Client and make request/call to server
        apiInterface= ApiInterface.ApiClient.getApiClient().create(ApiInterface.class);

        Call<ArrayList<DrinkDetails>> call = apiInterface.getAllDrinkDetails();

        Log.d(TAG,"Function Called ");

        call.enqueue(new Callback<ArrayList<DrinkDetails>>() {

            @Override
            public void onResponse(Call<ArrayList<DrinkDetails>> call, Response<ArrayList<DrinkDetails>> response) {
                Log.d(TAG,"Succeeded ");

                allDrinks = response.body();
//                Log.d(TAG, "onResponse: "+bars.get(0).getBarName());
//                Log.d(TAG, "onResponse: setting BarAdapter" + bars.size());
                classifyDrinks(allDrinks);



            }

            @Override
            public void onFailure(Call<ArrayList<DrinkDetails>> call, Throwable t) {

                Log.d(TAG,"failed - " + t.getMessage());

            }
        });
    }




    private void classifyDrinks(ArrayList<DrinkDetails> drinks) {

        HashSet<String> set = new HashSet<>();


        for (int i = 0; i<drinks.size(); i++){
            set.add(drinks.get(i).getDrinkType());
        }

        uniqueDrinkTypes = set.toArray(new String[set.size()]);

        Log.d(TAG, "classifyDrinks1: length of un - "+uniqueDrinkTypes.length);
        for(int i=0; i<uniqueDrinkTypes.length;i++){
            drinkOfDrinkTypes.put(uniqueDrinkTypes[i],new ArrayList<DrinkDetails>());
        }

        for(int i=0; i<drinks.size();i++){
            drinkOfDrinkTypes.get(drinks.get(i).getDrinkType()).add(drinks.get(i));
        }

    }

    private void setSpinnerDrinkType(View mView,Spinner spinnerDrinkType) {

        // Spinner Drop down elements
        List<String> drinkType = new ArrayList<String>();

        // Spinner click listener
        spinnerDrinkType.setOnItemSelectedListener(this);


       try{
           for(int i=0; i<uniqueDrinkTypes.length;i++){
               drinkType.add(uniqueDrinkTypes[i]);
           }
       }
       catch (NullPointerException e){
           Toast.makeText(MenuCardActivity.this, "Wait for a moment", Toast.LENGTH_LONG).show();
       }




        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, drinkType);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerDrinkType.setAdapter(dataAdapter);
    }
    private void setSpinnerDrink(View mView,String item) {
        ArrayList<DrinkDetails> list = drinkOfDrinkTypes.get(item);
        // Spinner Drop down elements
        List<String> drinkType = new ArrayList<String>();

        // Spinner click listener
        // spinnerDrinkType.setOnItemSelectedListener(this);


        Log.d(TAG, "setSpinnerDrink: size of drinks - "+list.size());
        for(int ii=0; ii<list.size();ii++){
            drinkType.add(list.get(ii).getDrinkName());
            Log.d(TAG, "setSpinnerDrink:  drink - "+drinkType.get(ii));
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_item, drinkType);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerDrinks.setAdapter(dataAdapter);

    }

    private void getMenuCard(){


        // getting API Client and make request/call to server
        apiInterface= ApiInterface.ApiClient.getApiClient().create(ApiInterface.class);

        Call<ArrayList<MenuCardDetails>> call = apiInterface.getDrinks(sessionBarId);

        Log.d(TAG,"Function Called with barID - "+sessionBarId);

        call.enqueue(new Callback<ArrayList<MenuCardDetails>>() {

            @Override
            public void onResponse(Call<ArrayList<MenuCardDetails>> call, Response<ArrayList<MenuCardDetails>> response) {
                Log.d(TAG,"Succeeded");

                drinks=response.body();
                mAdapter = new MenuCardDrinkTypeAdapter(drinks,mContext);
                rcyclerDrinkType.setAdapter(mAdapter);
                allDrinksId = new String[drinks.size()];
                for(int i = 0;i<drinks.size();i++){
                    allDrinksId[i] = drinks.get(i).getDrinkId();
                }


            }

            @Override
            public void onFailure(Call<ArrayList<MenuCardDetails>> call, Throwable t) {

                Log.d(TAG,"failed");

            }
        });


    }
    private void setToolBar() {
        toolbar = (Toolbar) findViewById(R.id.menuCardToolbar);

        setSupportActionBar(toolbar);

        ToolBarSetUp setUp = new ToolBarSetUp();
        setUp.setToolBar(MenuCardActivity.this, toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_manu,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.navMenuCard).setEnabled(false);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        // On selecting a spinner item
        String item = adapterView.getItemAtPosition(i).toString();
        //setting drink spinner
        setSpinnerDrink(view,item);


        // Showing selected spinner item
        // Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private boolean getSessionParams(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MenuCardActivity.this);
        sessionBarId = preferences.getString("@string/SessionBarId","");
        sessionPhone = preferences.getString("@string/SessionPhoneNumber","");
        return true;
    }
}
