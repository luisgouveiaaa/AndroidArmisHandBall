package com.example.debs.androidarmishandball.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ScrollingTabContainerView;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.restclient.RestProperties;
import com.example.debs.androidarmishandball.restclient.dto.Game;
import com.example.debs.androidarmishandball.restclient.dto.MatchDay;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.SQLOutput;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Luis Gouveia on 30/08/2017.
 */

public class MatchDayAdapter {

    private static final int MAX_BUFFER_SIZE = 5;
    private Activity mParentActivity;
    private MatchDay[] mMatchDays;
    private TabLayout matchDaysTab;
    private Map<MatchDay, Game[]> buffer;
    private int currentMatchDay;
    private Game[] currentMatchDayGames;

    public MatchDayAdapter (Activity activity, final MatchDay[] matchDays){
        mParentActivity = activity;
        mMatchDays = matchDays;
        buffer = new LinkedHashMap<>();
        matchDaysTab = (TabLayout) mParentActivity.findViewById(R.id.match_days_tab);
        matchDaysTab.setTabTextColors(Color.WHITE,Color.WHITE);
        matchDaysTab.setSelectedTabIndicatorColor(Color.WHITE);
        fillTabs();
        matchDaysTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentMatchDay = tab.getPosition();
                new HttpRequestTask().execute();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(buffer.size() < MAX_BUFFER_SIZE){
                    buffer.put(matchDays[currentMatchDay], currentMatchDayGames);
                }else{
                    buffer.remove(buffer.keySet().iterator().next());
                    buffer.put(matchDays[currentMatchDay], currentMatchDayGames);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                currentMatchDay = tab.getPosition();
                if(buffer.containsKey(matchDays[currentMatchDay])){
                    currentMatchDayGames = buffer.get(matchDays[currentMatchDay]);
                    fillRecycleView();
                }else{
                    new HttpRequestTask().execute();
                }
            }
        });
        matchDaysTab.setSelected(true);
    }

    private void fillRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mParentActivity.getBaseContext());
        RecyclerView editionsView = (RecyclerView) mParentActivity.findViewById(R.id.edition_matchdays_view);
        editionsView.setLayoutManager(layoutManager);
        editionsView.setHasFixedSize(true);
        editionsView.setAdapter(new GameAdapter(mParentActivity.getBaseContext(), currentMatchDayGames));
    }

    public void fillTabs(){
        for(int i = 0; i < mMatchDays.length; i++){
            matchDaysTab.addTab(matchDaysTab.newTab().setText(mMatchDays[i].getName()));
        }

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Game[]> {

        @Override
        protected Game[] doInBackground(Void... params) {

            RestProperties webProperties = new RestProperties(mParentActivity.getBaseContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getApiServerUri()
                            + webProperties.getGamesUri()).queryParam("matchDayPk", mMatchDays[currentMatchDay].getPk()).build();
            System.out.println(currentMatchDay);
            System.out.println(mMatchDays[currentMatchDay].getPk());
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<Game[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, Game[].class);

            Game[] objects = result.getBody();

            return objects;
        }

        @Override
        protected void onPostExecute(Game[] games) {

            currentMatchDayGames = games;
            fillRecycleView();
        }
    }
}
