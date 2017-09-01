package com.example.debs.androidarmishandball.activity;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.adapter.EditionAdapter;
import com.example.debs.androidarmishandball.restclient.RestProperties;
import com.example.debs.androidarmishandball.restclient.dto.Edition;
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
import java.util.List;
import java.util.Map;

public class TournamentActivity extends AppCompatActivity {

    private SearchResult tournamentInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        tournamentInfo = (SearchResult) this.getIntent().getSerializableExtra("tournament");

        TextView tournamentName = (TextView) findViewById(R.id.tournament_name);
        tournamentName.setText(tournamentInfo.getName());

        ImageView tournamentLogo = (ImageView) findViewById(R.id.tournament_logo);
        byte[] logo = tournamentInfo.getImage();
        tournamentLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences("favTournaments", MODE_PRIVATE).edit().putInt( tournamentInfo.getName(), tournamentInfo.getPk()).commit();
            }
        });
        if(logo != null) tournamentLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));

        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Edition[]> {

        @Override
        protected Edition[] doInBackground(Void... params) {

            RestProperties webProperties = new RestProperties(TournamentActivity.this.getBaseContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getApiServerUri()
                            + webProperties.getEditionsUri()).queryParam("tournamentPk", tournamentInfo.getPk()).build();

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<Edition[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, Edition[].class);

            Edition[] objects = result.getBody();

            return objects;
        }

        @Override
        protected void onPostExecute(Edition[] editions) {
            LinearLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 3);
            //layoutManager layoutManager = new LinearLayoutManager(getBaseContext());

            RecyclerView editionsView = (RecyclerView) findViewById(R.id.tournament_editions_view);
            editionsView.setLayoutManager(layoutManager);
            editionsView.setHasFixedSize(true);
            editionsView.setAdapter(new EditionAdapter(TournamentActivity.this.getBaseContext(), editions, tournamentInfo));
        }
    }
}
