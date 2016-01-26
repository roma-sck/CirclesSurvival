package com.example.sck.circles_survival.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sck.circles_survival.R;

public class HowToPlayFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_how_to_play, container, false);
        Button btnHowToPlay = (Button)getActivity().findViewById(R.id.btnHowToPlay);
        btnHowToPlay.setOnClickListener(btnHowToPlayOnClickListener);
        return view;
    }

    View.OnClickListener btnHowToPlayOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), ImagePagerViewActivity.class));
        }
    };

}

