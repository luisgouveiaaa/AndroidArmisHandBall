package com.example.debs.androidarmishandball.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debs.androidarmishandball.restclient.dto.GoalRecord;
import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.adapter.GoalRecordAdapter;
import com.example.debs.androidarmishandball.restclient.RestProperties;
import com.example.debs.androidarmishandball.restclient.dto.Game;

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

public class GameActivity extends AppCompatActivity {

    private Bundle mGameInfo;
    private GoalRecord[] homeTeamGoals;
    private GoalRecord[] visitorTeamGoals;
    public GameActivity() {
    }

    public void setHomeTeamGoals(GoalRecord[] homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public void setVisitorTeamGoals(GoalRecord[] visitorTeamGoals) {
        this.visitorTeamGoals = visitorTeamGoals;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mGameInfo = this.getIntent().getExtras();
        final Game game = (Game) mGameInfo.getSerializable("game");

        TextView tournamentInfo = (TextView) findViewById(R.id.game_tournament_info);
        TextView gameDate = (TextView) findViewById(R.id.game_date_info);
        TextView homeClubName = (TextView) findViewById(R.id.home_club_name);
        ImageView homeClubLogo = (ImageView) findViewById(R.id.home_club_icon);
        TextView homeClubScore = (TextView) findViewById(R.id.home_team_score);
        TextView visitorClubName = (TextView) findViewById(R.id.visitor_club_name);
        ImageView visitorClubLogo = (ImageView) findViewById(R.id.visitor_club_icon);
        TextView visitorClubScore = (TextView) findViewById(R.id.visitor_team_score);

        gameDate.setText(game.getDate());
        tournamentInfo.setText(game.getMatchDay());
        homeClubName.setText(game.getHomeTeam());
        byte[] logo = game.getHomeClubLogo();
        if(logo != null) homeClubLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
        homeClubScore.setText(String.valueOf(game.getHomeTeamScore()));
        visitorClubName.setText(game.getVisitorTeam());
        logo = game.getVisitorClubLogo();
        if(logo != null) visitorClubLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
        visitorClubScore.setText(String.valueOf(game.getVisitorTeamScore()));

        Button editHomeTeamResults = (Button) findViewById(R.id.edit_home_team_score_button);
        editHomeTeamResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SetGameResultsActivity.class);
                intent.putExtra("game", game);
                intent.putExtra("team", 0);
                startActivity(intent);
            }
        });
        Button editVisitorTeamResults = (Button) findViewById(R.id.edit_visitor_team_score_button);
        editVisitorTeamResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SetGameResultsActivity.class);
                intent.putExtra("game", game);
                intent.putExtra("team", 1);
                startActivity(intent);
            }
        });
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, GoalRecord[]> {

        @Override
        protected GoalRecord[] doInBackground(Void... params) {
            Game game = (Game) mGameInfo.getSerializable("game");
            RestProperties webProperties = new RestProperties(GameActivity.this.getBaseContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getApiServerUri()
                            + webProperties.getGoalRecordUri()).queryParam("gamePk", game.getPk()).build();

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<GoalRecord[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, GoalRecord[].class);

            GoalRecord[] objects = result.getBody();

            System.out.println(objects.toString());
            return objects;
        }

        @Override
        protected void onPostExecute(GoalRecord[] goals) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getBaseContext());


            List<GoalRecord> homeTeamGoals = new ArrayList<>();
            List<GoalRecord> visitorTeamGoals = new ArrayList<>();

            for(int i = 0; i < goals.length; i++){
                GoalRecord goal = goals[i];
                if(goal.getTeam() == 0){
                    homeTeamGoals.add(goal);
                }else if(goal.getTeam() == 1){
                    visitorTeamGoals.add(goal);
                }
            }

            RecyclerView homeTeamGoalRecordView = (RecyclerView) findViewById(R.id.home_team_goal_record_view);
            homeTeamGoalRecordView.setLayoutManager(linearLayoutManager);
            homeTeamGoalRecordView.setHasFixedSize(true);

            RecyclerView visitorTeamGoalRecordView = (RecyclerView) findViewById(R.id.visitor_team_goal_record_view);
            visitorTeamGoalRecordView.setLayoutManager(linearLayoutManager2);
            visitorTeamGoalRecordView.setHasFixedSize(true);


            homeTeamGoalRecordView.setAdapter(new GoalRecordAdapter(GameActivity.this.getBaseContext(), homeTeamGoals.toArray(new GoalRecord[homeTeamGoals.size()])));
            GameActivity.this.setHomeTeamGoals(homeTeamGoals.toArray(new GoalRecord[homeTeamGoals.size()]));
            visitorTeamGoalRecordView.setAdapter(new GoalRecordAdapter(GameActivity.this.getBaseContext(), visitorTeamGoals.toArray(new GoalRecord[visitorTeamGoals.size()])));
            GameActivity.this.setVisitorTeamGoals(visitorTeamGoals.toArray(new GoalRecord[visitorTeamGoals.size()]));
        }
    }
}
