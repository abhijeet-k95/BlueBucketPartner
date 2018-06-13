package com.unical.bluebucket.bluebucketpartner.MenuCard.RecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.unical.bluebucket.bluebucketpartner.MenuCard.Model.MenuCardDetails;
import com.unical.bluebucket.bluebucketpartner.R;

import java.util.ArrayList;

public class MenuCardDrinksAdapter extends RecyclerView.Adapter<MenuCardDrinksAdapter.MyViewHolder> {

    private static final String TAG = "MenuCardDrinksAdapter";



    private ArrayList<MenuCardDetails> drinks;
    int [] drinksIndex;
    private Context mContext;


    public MenuCardDrinksAdapter(ArrayList<MenuCardDetails> drinks, int[] drinksIndex, Context mContext) {
        this.drinks = drinks;
        this.drinksIndex = drinksIndex;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.snippet_menu_card_drink_rate, parent, false);
        return new MenuCardDrinksAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.rlDrinkName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!holder.tgButtonDrinkName.isChecked()){

                    holder.tgButtonDrinkName.setChecked(true);
                    Log.d(TAG, "onClick: toggle buttn is checked ");
                    holder.rlDrinkRate.setVisibility(View.VISIBLE);


                }
                else{

                    holder.tgButtonDrinkName.setChecked(false);
                    Log.d(TAG, "onClick: toggle buttn is unchecked ");
                    holder.rlDrinkRate.setVisibility(View.GONE);

                }
            }
        });

        holder.tgButtonDrinkName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.tgButtonDrinkName.isChecked()){

                    Log.d(TAG, "onClick: toggle buttn is checked ");
                    holder.rlDrinkRate.setVisibility(View.VISIBLE);


                }
                else{

                    Log.d(TAG, "onClick: toggle buttn is unchecked ");
                    holder.rlDrinkRate.setVisibility(View.GONE);

                }

            }
        });

        int newPos = drinksIndex[position];

        holder.tvDrinkName.setText(drinks.get(newPos).getDrinkName());

        holder.rt30ml.setText(drinks.get(newPos).getMl30());
        holder.rt60ml.setText(drinks.get(newPos).getMl60());
        holder.rt90ml.setText(drinks.get(newPos).getMl90());
        holder.rt120ml.setText(drinks.get(newPos).getMl120());
        holder.rt150ml.setText(drinks.get(newPos).getMl150());
        holder.rt180ml.setText(drinks.get(newPos).getMl180());



    }

    @Override
    public int getItemCount() {
        return drinksIndex.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDrinkName,rt30ml,rt60ml,rt90ml,rt120ml,rt150ml,rt180ml;
        RelativeLayout rlDrinkName,rlDrinkRate;
        ToggleButton tgButtonDrinkName;
        public MyViewHolder(View itemView) {
            super(itemView);
            rlDrinkName = itemView.findViewById(R.id.rl_drink_name);
            tvDrinkName = itemView.findViewById(R.id.tv_drink_name);
            tgButtonDrinkName = itemView.findViewById(R.id.toggle_btn_drink_name);
            rlDrinkRate = itemView.findViewById(R.id.rl_drink_rate);
            rt30ml = itemView.findViewById(R.id.rate_30ml);
            rt60ml = itemView.findViewById(R.id.rate_60ml);
            rt90ml = itemView.findViewById(R.id.rate_90ml);
            rt120ml = itemView.findViewById(R.id.rate_120ml);
            rt150ml = itemView.findViewById(R.id.rate_150ml);
            rt180ml = itemView.findViewById(R.id.rate_180ml);
        }
    }
}
