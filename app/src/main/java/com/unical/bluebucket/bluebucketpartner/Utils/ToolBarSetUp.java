package com.unical.bluebucket.bluebucketpartner.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.unical.bluebucket.bluebucketpartner.Account.AccountActivity;
import com.unical.bluebucket.bluebucketpartner.MenuCard.MenuCardActivity;
import com.unical.bluebucket.bluebucketpartner.Orders.OrdersActivity;
import com.unical.bluebucket.bluebucketpartner.R;

public class ToolBarSetUp {
    private static final String TAG = "ToolBarSetUp";
    Toolbar toolbar ;


    public void setToolBar(final Context ctx,Toolbar toolbar) {

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d(TAG, "onMenuItemClick: Clicked menuItem"+item);

                switch (item.getItemId()){


                    case R.id.navMenuCard :
                        Intent menucard =new Intent(ctx,MenuCardActivity.class);
                        ctx.startActivity(menucard);
                        break;

                    case R.id.navOrders :
                        Intent orders =new Intent(ctx,OrdersActivity.class);
                        ctx.startActivity(orders);

                        break;

                    case R.id.navAccount :
                        Intent account =new Intent(ctx,AccountActivity.class);
                        ctx.startActivity(account);

                        break;

                    case R.id.navLogOut :
                        Logout lg = new Logout(ctx);
                        break;
                }


                return false;
            }
        });
    }
}
