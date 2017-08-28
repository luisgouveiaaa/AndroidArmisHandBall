package com.example.debs.androidarmishandball.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.debs.androidarmishandball.restclient.dto.Game;
import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.restclient.RestProperties;
import com.example.debs.androidarmishandball.adapter.GameAdapter;

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
 * {@link HomeFragment} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private View rootView;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        new HttpRequestTask().execute();
        return rootView;
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Game[]> {

        @Override
        protected Game[] doInBackground(Void... params) {
            RestProperties webProperties = new RestProperties(HomeFragment.this.getContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getApiServerUri() + webProperties.getGamesUri()).build();

            HttpHeaders headers = new HttpHeaders();

            HttpEntity<Game[]> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<Game[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, Game[].class);

            Game[] objects = result.getBody();

            return objects;
        }

        @Override
        protected void onPostExecute(Game[] games) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.all_games_view);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);

            recyclerView.setAdapter(new GameAdapter(HomeFragment.this.getContext(), games));
        }
    }


}


