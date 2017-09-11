package com.example.debs.androidarmishandball.activity;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.adapter.GoalRecordUpdateAdapter;
import com.example.debs.androidarmishandball.restclient.RestProperties;
import com.example.debs.androidarmishandball.restclient.dto.Game;
import com.example.debs.androidarmishandball.restclient.dto.GoalRecord;
import com.example.debs.androidarmishandball.restclient.dto.SimpleContent;

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

public class SetGameResultsActivity extends AppCompatActivity {


    private Game game;
    private int teamPk;
    private int teamType;
    private GoalRecord[] mGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_game_results);
        TextView gameName = (TextView) findViewById(R.id.game_teams);

        TextView teamName = (TextView) findViewById(R.id.club_name);
        ImageView teamLogo = (ImageView) findViewById(R.id.club_icon);
        TextView teamScore = (TextView) findViewById(R.id.team_score);

        game = (Game) getIntent().getSerializableExtra("game");
        teamType = (int) getIntent().getSerializableExtra("team");


        if (teamType == 0) {
            teamName.setText(game.getHomeTeam());
            byte[] logo = game.getHomeClubLogo();
            if (logo != null)
                teamLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
            teamScore.setText(String.valueOf(game.getHomeTeamScore()));
            teamPk = game.getHomeTeamPk();
        } else {
            teamName.setText(game.getVisitorTeam());
            byte[] logo = game.getVisitorClubLogo();
            if (logo != null)
            teamScore.setText(String.valueOf(game.getVisitorTeamScore()));
            teamPk = game.getVisitorTeamPk();
        }
        gameName.setText(game.getHomeTeam() + " vs " + game.getVisitorTeam());

        new GoalRecordsRequestTask().execute();
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, SimpleContent[]> {

        @Override
        protected SimpleContent[] doInBackground(Void... params) {

            RestProperties webProperties = new RestProperties(SetGameResultsActivity.this.getBaseContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getApiServerUri()
                            + webProperties.getAthletesUri()).queryParam("teamPk", teamPk).build();

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<SimpleContent[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, SimpleContent[].class);

            SimpleContent[] objects = result.getBody();

            return objects;
        }

        @Override
        protected void onPostExecute(SimpleContent[] players) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());

            System.out.println(mGoals);
            List<GoalRecord> teamGoals = new ArrayList<>();
            for (int i = 0; i < mGoals.length; i++) {
                if(mGoals[i].getTeam() == teamType){
                    teamGoals.add(mGoals[i]);
                }
            }

            for (int i = 0; i < players.length; i++){
                boolean existsFlag = false;
                for(GoalRecord goalRecord : teamGoals){
                    if(goalRecord.getAhtletePk() == players[i].getPk()){
                        existsFlag = true;
                        break;
                    }
                }
                if(!existsFlag){
                    teamGoals.add(new GoalRecord(-1, teamType, game.getPk(), players[i].getName(),players[i].getImage(), Byte.valueOf("0")));
                }
            }

            RecyclerView homeTeamGoalRecordView = (RecyclerView) findViewById(R.id.team_goal_record_view);
            homeTeamGoalRecordView.setLayoutManager(linearLayoutManager);
            homeTeamGoalRecordView.setHasFixedSize(true);
            homeTeamGoalRecordView.setAdapter(new GoalRecordUpdateAdapter(SetGameResultsActivity.this, teamGoals.toArray(new GoalRecord[teamGoals.size()])));
        }
    }

    private class GoalRecordsRequestTask extends AsyncTask<Void, Void, GoalRecord[]> {

        @Override
        protected GoalRecord[] doInBackground(Void... params) {
            Game game = (Game) SetGameResultsActivity.this.getIntent().getSerializableExtra("game");
            RestProperties webProperties = new RestProperties(SetGameResultsActivity.this.getBaseContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getApiServerUri()
                            + webProperties.getGoalRecordUri()).queryParam("gamePk", game.getPk()).build();

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<GoalRecord[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, GoalRecord[].class);

            GoalRecord[] objects = result.getBody();
            mGoals = objects;
            return objects;
        }

        @Override
        protected void onPostExecute(GoalRecord[] goals) {
            new HttpRequestTask().execute();
        }
    }
}


