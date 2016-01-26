package com.example.sck.circles_survival.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sck.circles_survival.R;

public class ImageFragment extends Fragment {

    private Integer imgsItemData;
    private Bitmap myBitmap;
    private ImageView imageView;

    public static ImageFragment newInstance() {
        return new ImageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.image_view, container, false);
        imageView = (ImageView) root.findViewById(R.id.imageView);
        setImageInViewPager();
        return root;
    }

    public void setImageList(Integer integer) {
        this.imgsItemData = integer;
    }

    public void setImageInViewPager() {
        try {
            myBitmap = BitmapFactory.decodeResource(getResources(), imgsItemData);
            if (myBitmap != null) {
                try {
                    if (imageView != null) {
                        imageView.setImageBitmap(myBitmap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (myBitmap != null) {
            myBitmap.recycle();
            myBitmap = null;
        }
    }
}
