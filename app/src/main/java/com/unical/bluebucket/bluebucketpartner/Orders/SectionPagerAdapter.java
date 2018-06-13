package com.unical.bluebucket.bluebucketpartner.Orders;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "SectionPagerAdapter";
    private final List<Fragment> mFragList = new ArrayList<>();
    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragList.get(position);
    }

    @Override
    public int getCount() {
        return mFragList.size();
    }

    public void addFrag(Fragment frag){
        mFragList.add(frag);
    }
}
