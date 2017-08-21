package com.example.debs.androidarmishandball.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.debs.androidarmishandball.Game;
import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.adapter.SearchAdapter;
import com.example.debs.androidarmishandball.restclient.AppSession;
import com.example.debs.androidarmishandball.restclient.RestProperties;
import com.example.debs.androidarmishandball.restclient.dto.SearchResult;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Debs on 13/08/17.
 */

public class SearchFragment extends Fragment {

    private View rootView;
    private String search;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
        rootView =  inflater.inflate(R.layout.fragment_search, container, false);
        SearchView searchBar = (SearchView) rootView.findViewById(R.id.search_bar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search = query;
                new HttpRequestTask().execute();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search = newText;
                new HttpRequestTask().execute();
                return true;
            }
        });
        return rootView;
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, SearchResult[]> {

        @Override
        protected SearchResult[] doInBackground(Void... params) {
            RestProperties webProperties = new RestProperties(SearchFragment.this.getContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getApiServerUri() + webProperties.getSearchUri()).queryParam("name", search).build();

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<SearchResult[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, SearchResult[].class);

            SearchResult[] objects = result.getBody();

            return objects;
        }

        @Override
        protected void onPostExecute(SearchResult[] results) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.search_results_view);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);

            recyclerView.setAdapter(new SearchAdapter(SearchFragment.this.getContext(), results));
        }
    }
}

