package com.unical.bluebucket.bluebucketpartner.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.unical.bluebucket.bluebucketpartner.LogIn.LogInActivity;
import com.unical.bluebucket.bluebucketpartner.R;
import com.unical.bluebucket.bluebucketpartner.Utils.Logout;
import com.unical.bluebucket.bluebucketpartner.Utils.ToolBarSetUp;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setToolBar();
    }

    private void setToolBar() {
        toolbar=(Toolbar)findViewById(R.id.homeToolbar);
        setSupportActionBar(toolbar);

        ToolBarSetUp setUp = new ToolBarSetUp();
        setUp.setToolBar(HomeActivity.this,toolbar);

//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Log.d(TAG, "onMenuItemClick: Clicked menuItem"+item);
//
//                switch (item.getItemId()){
//                    case R.id.navLogOut :
//                        Logout lg = new Logout(HomeActivity.this);
//                        break;
//                }
//
//
//                return false;
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_manu,menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
