package com.example.alphabets;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class listFrag extends ListFragment {

    Itemselected activity;

    public interface Itemselected
    {
        void onItemSelected(int index) throws IllegalAccessException;

    }

    public listFrag() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (Itemselected) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String [] data = getResources().getStringArray(R.array.title);


        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data));

        //the phone is in landscape mode
        if (this.getActivity().findViewById(R.id.layout_portrait) == null)
        {
            try {
                activity.onItemSelected(0);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {

        try {
            activity.onItemSelected(position);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
