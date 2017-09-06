package com.example.debs.androidarmishandball.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
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

import java.util.Map;

/**
 * Created by Debs on 13/08/17.
 */

public class SearchFragment extends Fragment {

    private View rootView;
    private String search;
    private RecyclerView.ItemDecoration categoryDivider;

    public SearchFragment() {
        // Required empty public constructor
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

    private class HttpRequestTask extends AsyncTask<Void, Void, SimpleContent[]> {

        @Override
        protected SimpleContent[] doInBackground(Void... params) {
            RestProperties webProperties = new RestProperties(SearchFragment.this.getContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getApiServerUri() + webProperties.getSearchUri()).queryParam("name", search).build();

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<SimpleContent[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, SimpleContent[].class);

            SimpleContent[] objects = result.getBody();

            return objects;
        }

        @Override
        protected void onPostExecute(final SimpleContent[] results) {


            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.search_results_view);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new SearchAdapter(SearchFragment.this.getContext(), results));
            if(categoryDivider == null) {
                categoryDivider = new RecyclerView.ItemDecoration() {

                    private int textSize = 30;
                    private int groupSpacing = 100;

                    private Paint paint = new Paint();

                    {
                        paint.setTextSize(textSize);
                        paint.setFakeBoldText(true);
                        paint.setTypeface(Typeface.DEFAULT);
                        paint.setAntiAlias(true);
                        paint.setColor(Color.DKGRAY);
                    }

                    @Override
                    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                        for (int i = 0; i < parent.getChildCount(); i++) {
                            View view = parent.getChildAt(i);
                            int position = parent.getChildAdapterPosition(view);
                            if (results.length > 0 && position < results.length) {
                                if (position == 0 || !results[position].getType().name().equals((results[position - 1].getType().name()))) {
                                    c.drawText(results[position].getType().name(), view.getLeft() + 30,
                                            view.getTop() - groupSpacing / 2 + textSize / 3, paint);
                                }
                            }
                        }
                    }

                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        int position = parent.getChildAdapterPosition(view);
                        if (results.length > 0 && position < results.length) {
                            if (position == 0 || !results[position].getType().name().equals((results[position - 1].getType().name()))) {
                                outRect.set(0, groupSpacing, 0, 0);
                            }else{
                                outRect.setEmpty();
                            }
                        }
                    }

                };
                recyclerView.addItemDecoration(categoryDivider);
            }else{
                recyclerView.invalidateItemDecorations();
            }
        }
    }
}

