package com.example.sck.circles_survival.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.sck.circles_survival.Images;
import com.example.sck.circles_survival.R;
import com.example.sck.circles_survival.adapters.MyFragmentPagerAdapter;

import java.util.ArrayList;

public class ImagePagerViewActivity extends AppCompatActivity implements
        OnClickListener, ViewPager.OnPageChangeListener {

    private Button btnImagePrevious, btnImageNext;
    private int position = 0, totalImage;
    private ViewPager viewPager;
    private TextView tvImgInstructions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);

        findViews();

        Images imageId = new Images();
        final ArrayList<Integer> itemData = imageId.getImageItem();
        totalImage = itemData.size();

        setPage(position);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), itemData);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(ImagePagerViewActivity.this);

        btnImagePrevious.setOnClickListener(this);
        btnImageNext.setOnClickListener(this);

    }

    public void findViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        btnImagePrevious = (Button) findViewById(R.id.btnImagePrevious);
        btnImageNext = (Button) findViewById(R.id.btnImageNext);
        tvImgInstructions = (TextView) findViewById(R.id.text_image_instructions);
    }

    @Override
    public void onClick(View v) {
        if (v == btnImagePrevious) {
            position--;
            viewPager.setCurrentItem(position);
        } else if (v == btnImageNext) {
            position++;
            viewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
        setPage(position);
    }

    private void setPage(int page) {
        if (page == 0 && totalImage > 0) {
            btnImageNext.setVisibility(View.VISIBLE);
            btnImagePrevious.setVisibility(View.INVISIBLE);
        } else if (page == totalImage - 1 && totalImage > 0) {
            btnImageNext.setVisibility(View.INVISIBLE);
            btnImagePrevious.setVisibility(View.VISIBLE);
        } else {
            btnImageNext.setVisibility(View.VISIBLE);
            btnImagePrevious.setVisibility(View.VISIBLE);
        }
        tvImgInstructions.setText(getResources().getStringArray(R.array.images_instructions)[page]);
    }
}
