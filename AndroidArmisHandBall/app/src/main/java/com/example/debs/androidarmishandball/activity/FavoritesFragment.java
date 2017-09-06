package com.example.debs.androidarmishandball.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.restclient.dto.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * FavoritesFragment.OnFragmentInteractionListener interface
 * to handle interaction events.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment {

    private View rootView;

    public FavoritesFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment FavoritesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        NestedListView teamsList = (NestedListView) rootView.findViewById(R.id.favorite_teams_list);
        Map<String, ?> favorites = getContext().getSharedPreferences("favoriteTeams", Context.MODE_PRIVATE).getAll();
        ArrayList<String> teams = new ArrayList<>();
        teams.addAll(favorites.keySet());
        CustomAdapter adapter = new CustomAdapter(getActivity(),  R.layout.list_item, teams);
        teamsList.setAdapter(adapter);
        return rootView;
    }

    public class CustomAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final int resourceID;
        private ArrayList<String> teams;

        public CustomAdapter(Context context, int resource, ArrayList<String> teams) {
            super(context, resource, teams);
            this.teams = teams;
            this.context = context;
            this.resourceID = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(resourceID, parent, false);
            TextView name = (TextView) rowView.findViewById(R.id.item_name);
            name.setText(String.valueOf(teams.get(position)));
            return rowView;
        }
    }



}
