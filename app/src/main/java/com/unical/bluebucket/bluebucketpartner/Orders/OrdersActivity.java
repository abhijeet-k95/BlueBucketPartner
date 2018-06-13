package com.unical.bluebucket.bluebucketpartner.Orders;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.unical.bluebucket.bluebucketpartner.Orders.NewOrders.NewOrdersFragment;
import com.unical.bluebucket.bluebucketpartner.Orders.OnGoingOrders.OngoingOrdersFragment;
import com.unical.bluebucket.bluebucketpartner.R;

public class OrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setUpViewPager();
    }

    private void setUpViewPager() {

        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OngoingOrdersFragment());
        adapter.addFrag(new NewOrdersFragment());
        ViewPager viewPager =  findViewById(R.id.container_new_ongoing_orders);
        viewPager.setAdapter(adapter);
    }
}
