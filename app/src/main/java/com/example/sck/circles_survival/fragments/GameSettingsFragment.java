package com.example.sck.circles_survival.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sck.circles_survival.App;
import com.example.sck.circles_survival.activities.MainActivity;
import com.example.sck.circles_survival.adapters.ExpandListAdapter;
import com.example.sck.circles_survival.R;

import java.util.ArrayList;
import java.util.Arrays;

public class GameSettingsFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addSpinner();

        addExpandableListView();
    }

    /**
     * add spinner with set of possible numbers of enemies in game
     */
    private void addSpinner() {
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner_numOfEnemies);

        // array with numbers of enemies
        final Integer[] arr_numOfEnemies = {10, 15, 5};

        // create adapter
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_item, arr_numOfEnemies);

        // specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // apply the adapter
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(App.getContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

//                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putInt(getString(R.string.saved_max_enemies), arr_numOfEnemies[position]);
//                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    /**
     * add expandableList with colors to select the game background
     */
    private void addExpandableListView() {
        ExpandableListView eListView = (ExpandableListView)getActivity().findViewById(R.id.exListView);

        // create data set for adapter
        ArrayList<ArrayList<String>> groups = new ArrayList<>();

        final ArrayList<String> bg_colors = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.bground_colors)));
        groups.add(bg_colors);

        // create and apply the adapter
        ExpandListAdapter adapter = new ExpandListAdapter(App.getContext(), groups);
        eListView.setAdapter(adapter);

        eListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                int index = parent.getFlatListPosition(ExpandableListView
                        .getPackedPositionForChild(groupPosition, childPosition));
                // checked the selected item ( row_highlighter selector )
                parent.setItemChecked(index, true);

                // set game background color
                String selectedBGcolor = getResources().getStringArray(R.array.bground_colors)[childPosition];
                MainActivity.setGameBGcolor(selectedBGcolor);

                return false;
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_colors, container, false);
    }

}