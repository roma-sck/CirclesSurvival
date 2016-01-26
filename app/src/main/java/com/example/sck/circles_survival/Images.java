package com.example.sck.circles_survival;

import java.util.ArrayList;

public final class Images {

    private ArrayList<Integer> imageId;

    public Images() {
        imageId = new ArrayList<>();
        imageId.add(R.drawable.how_to_play_img1);
        imageId.add(R.drawable.how_to_play_img2);
        imageId.add(R.drawable.how_to_play_img3);
        imageId.add(R.drawable.how_to_play_img4);
        imageId.add(R.drawable.how_to_play_img5);
    }
    public ArrayList<Integer> getImageItem() {
        return imageId;
    }
}
