package com.example.debs.androidarmishandball.activity;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.adapter.MatchDayAdapter;
import com.example.debs.androidarmishandball.restclient.RestProperties;
import com.example.debs.androidarmishandball.restclient.dto.Edition;
import com.example.debs.androidarmishandball.restclient.dto.MatchDay;
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

public class EditionActivity extends AppCompatActivity {

    private SimpleContent tournamentInfo;
    private Edition editionInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

        tournamentInfo = (SimpleContent) this.getIntent().getSerializableExtra("tournament");
        editionInfo = (Edition) this.getIntent().getSerializableExtra("edition");

        TextView tournamentName = (TextView) findViewById(R.id.edition_tournament_name);
        tournamentName.setText(tournamentInfo.getName());
        TextView editionDate = (TextView) findViewById(R.id.edition_date);
        editionDate.setText(editionInfo.getSeason());

        ImageView tournamentLogo = (ImageView) findViewById(R.id.edition_tournament_logo);
        byte[] logo = tournamentInfo.getImage();
        if(logo != null) tournamentLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));



        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, MatchDay[]> {

        @Override
        protected MatchDay[] doInBackground(Void... params) {

            RestProperties webProperties = new RestProperties(EditionActivity.this.getBaseContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getApiServerUri()
                            + webProperties.getMatchDaysUri()).queryParam("editionPk", editionInfo.getPk()).build();

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<MatchDay[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, MatchDay[].class);

            MatchDay[] objects = result.getBody();

            return objects;
        }

        @Override
        protected void onPostExecute(MatchDay[] matchDays) {
            new MatchDayAdapter(EditionActivity.this, matchDays);

            /*RecyclerView editionsView = (RecyclerView) findViewById(R.id.tournament_editions_view);
            editionsView.setLayoutManager(layoutManager);
            editionsView.setHasFixedSize(true);
            editionsView.setAdapter(new EditionAdapter(TournamentActivity.this.getBaseContext(), editions));*/
        }
    }
}
