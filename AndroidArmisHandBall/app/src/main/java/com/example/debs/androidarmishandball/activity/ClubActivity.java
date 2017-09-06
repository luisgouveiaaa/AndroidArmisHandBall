package com.example.debs.androidarmishandball.activity;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.adapter.TeamAdapter;
import com.example.debs.androidarmishandball.restclient.RestProperties;
import com.example.debs.androidarmishandball.restclient.dto.SimpleContent;
import com.example.debs.androidarmishandball.restclient.dto.Team;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class ClubActivity extends AppCompatActivity {

    private SimpleContent clubInfo;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        clubInfo = (SimpleContent) this.getIntent().getSerializableExtra("club");

        TextView clubName = (TextView) findViewById(R.id.club_name);
        clubName.setText(clubInfo.getName());

        ImageView clubLogo = (ImageView) findViewById(R.id.club_logo);
        byte[] logo = clubInfo.getImage();
        /*clubLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences("favoriteClubs", MODE_PRIVATE).edit().putInt( clubInfo.getName(), clubInfo.getPk()).commit();
            }
        });*/
        if(logo != null) clubLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
        final ImageButton favoriteButton = (ImageButton) findViewById(R.id.favorite_button);
        if(getSharedPreferences("favoriteClubs", MODE_PRIVATE).contains(clubInfo.getName())){
            favoriteButton.setBackgroundResource(R.drawable.favorite_full_white_icon);
            isFavorite = true;
        }else{
            isFavorite = false;
        }
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFavorite){
                    getSharedPreferences("favoriteClubs", MODE_PRIVATE).edit().remove(clubInfo.getName()).commit();
                    Toast.makeText(ClubActivity.this,"Removed from Favorites",Toast.LENGTH_SHORT).show();
                    favoriteButton.setBackgroundResource(R.drawable.favorite_white_icon);
                    isFavorite = false;
                }else{
                    getSharedPreferences("favoriteClubs", MODE_PRIVATE).edit().putInt(clubInfo.getName(), clubInfo.getPk()).commit();
                    Toast.makeText(ClubActivity.this,"Added to Favorites",Toast.LENGTH_SHORT).show();
                    favoriteButton.setBackgroundResource(R.drawable.favorite_full_white_icon);
                    isFavorite = true;
                }

            }
        });

       new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Team[]> {

        @Override
        protected Team[] doInBackground(Void... params) {

            RestProperties webProperties = new RestProperties(ClubActivity.this.getBaseContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getApiServerUri()
                            + webProperties.getTeamsUri()).queryParam("clubPk", clubInfo.getPk()).build();

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<Team[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, Team[].class);

            Team[] objects = result.getBody();

            return objects;
        }

        @Override
        protected void onPostExecute(Team[] teams) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());

            RecyclerView editionsView = (RecyclerView) findViewById(R.id.club_teams_view);
            editionsView.setLayoutManager(layoutManager);
            editionsView.setHasFixedSize(true);
            editionsView.setAdapter(new TeamAdapter(ClubActivity.this.getBaseContext(), teams, clubInfo));
        }
    }
}
