package com.example.debs.androidarmishandball;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Debs on 13/08/17.
 */

public class SearchFragment extends Fragment {

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search, container, false);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        //to refresh the lists
    }
}
