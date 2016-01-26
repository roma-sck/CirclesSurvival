package com.example.sck.circles_survival.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sck.circles_survival.R;
import com.example.sck.circles_survival.activities.ImagePagerViewActivity;

public class HowToPlayFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btnHowToPlay = (Button)getActivity().findViewById(R.id.btnHowToPlay);
        btnHowToPlay.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_how_to_play, container, false);
    }

    public void onClick(View view) {
        startActivity(new Intent(getActivity(), ImagePagerViewActivity.class));
    }
}

