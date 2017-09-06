package com.example.debs.androidarmishandball.activity;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.adapter.GameAdapter;
import com.example.debs.androidarmishandball.adapter.SearchAdapter;
import com.example.debs.androidarmishandball.adapter.ViewPagerAdapter;
import com.example.debs.androidarmishandball.restclient.RestProperties;
import com.example.debs.androidarmishandball.restclient.dto.Game;
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

public class TeamActivity extends AppCompatActivity {

    private SimpleContent clubInfo;
    private Team teamInfo;
    private SimpleContent[] mAthletes;
    private Game[] mGames;
    private boolean isFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        clubInfo = (SimpleContent) this.getIntent().getSerializableExtra("club");
        teamInfo = (Team) this.getIntent().getSerializableExtra("team");

        TextView clubName = (TextView) findViewById(R.id.club_name);
        clubName.setText(clubInfo.getName());

        ImageView clubLogo = (ImageView) findViewById(R.id.club_logo);
        byte[] logo = clubInfo.getImage();
        if(logo != null) clubLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
        final ImageButton favoriteButton = (ImageButton) findViewById(R.id.favorite_button);
        if(getSharedPreferences("favoriteTeams", MODE_PRIVATE).contains(teamInfo.getGender() +" - " + teamInfo.getAgeRange())){
            favoriteButton.setBackgroundResource(R.drawable.favorite_full_white_icon);
            isFavorite = true;
        }else{
            isFavorite = false;
        }
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFavorite){
                    getSharedPreferences("favoriteTeams", MODE_PRIVATE).edit().remove( teamInfo.getGender() +" - " + teamInfo.getAgeRange()).commit();
                    getSharedPreferences("favoriteTeams", MODE_PRIVATE).edit().remove(String.valueOf(teamInfo.getPk())).commit();
                    Toast.makeText(TeamActivity.this,"Removed from Favorites",Toast.LENGTH_SHORT).show();
                    favoriteButton.setBackgroundResource(R.drawable.favorite_white_icon);
                    isFavorite = false;
                }else{
                    getSharedPreferences("favoriteTeams", MODE_PRIVATE).edit().putInt(teamInfo.getGender() +" - " + teamInfo.getAgeRange(), teamInfo.getPk()).commit();
                    getSharedPreferences("favoriteTeams", MODE_PRIVATE).edit().putInt(String.valueOf(teamInfo.getPk()), clubInfo.getPk()).commit();
                    Toast.makeText(TeamActivity.this,"Added to Favorites",Toast.LENGTH_SHORT).show();
                    favoriteButton.setBackgroundResource(R.drawable.favorite_full_white_icon);
                    isFavorite = true;
                }

            }
        });
        TextView ageRangeGender = (TextView) findViewById(R.id.team_age_range_gender);
        ageRangeGender.setText(String.format("%s - %s", teamInfo.getAgeRange(), teamInfo.getGender()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_view);
        ViewPager viewPager = (ViewPager) findViewById(R.id.content_view_pager);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(TeamAthletesFragment.newInstance(teamInfo.getPk()), "Players");
        adapter.addFrag(TeamGamesFragment.newInstance(teamInfo.getPk()), "Games");
        viewPager.setAdapter(adapter);
    }




}
