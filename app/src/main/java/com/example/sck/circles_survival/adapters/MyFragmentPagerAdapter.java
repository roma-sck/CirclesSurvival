package com.example.sck.circles_survival.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.sck.circles_survival.fragments.ImageFragment;

import java.util.ArrayList;

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Integer> itemData;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Integer> itemData) {
        super(fm);
        this.itemData = itemData;
    }

    @Override
    public int getCount() {
        return itemData.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        ImageFragment f = ImageFragment.newInstance();
        f.setImageList(itemData.get(position));
        return f;
    }
}
