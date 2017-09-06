package com.example.debs.androidarmishandball.activity;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.adapter.SearchAdapter;
import com.example.debs.androidarmishandball.restclient.RestProperties;
import com.example.debs.androidarmishandball.restclient.dto.SimpleContent;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * TeamAthletesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamAthletesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamAthletesFragment extends Fragment {


    private View rootView;

    public TeamAthletesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment TeamAthletesFragment.
     */

    public static TeamAthletesFragment newInstance(int teamPk) {
        TeamAthletesFragment fragment = new TeamAthletesFragment();
        Bundle args = new Bundle();
        args.putInt("pk", teamPk);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TeamAthletesRequestTask().execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        return rootView;
    }


    private class TeamAthletesRequestTask extends AsyncTask<Void, Void, SimpleContent[]> {

        @Override
        protected SimpleContent[] doInBackground(Void... params) {
            RestProperties webProperties = new RestProperties(TeamAthletesFragment.this.getContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getApiServerUri() +
                            webProperties.getAthletesUri()).queryParam("teamPk", getArguments().getInt("pk")).build();

            HttpHeaders headers = new HttpHeaders();

            HttpEntity<SimpleContent[]> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<SimpleContent[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, SimpleContent[].class);

            SimpleContent[] objects = result.getBody();

            return objects;
        }

        @Override
        protected void onPostExecute(final SimpleContent[] athletes) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TeamAthletesFragment.this.getContext());
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.content_view);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new SearchAdapter(TeamAthletesFragment.this.getContext(), athletes));
        }
    }

}
