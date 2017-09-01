package com.example.debs.androidarmishandball.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debs.androidarmishandball.restclient.dto.Game;
import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.activity.GameActivity;
import com.example.debs.androidarmishandball.activity.MainActivity;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{

    private Context mContext;
    private Game[] mGames;

    public GameAdapter(Context context, Game[] games){
        this.mContext = context;
        this.mGames = games;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game game = mGames[position];
        holder.extras.putExtra("game", game);
        holder.gameDate.setText(game.getDate());
        holder.tournamentInfo.setText(game.getMatchDay());
        holder.homeClubName.setText(game.getHomeTeam());
        byte[] logo = game.getHomeClubLogo();
        if(logo != null) holder.homeClubLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
        holder.homeClubScore.setText(String.valueOf(game.getHomeTeamScore()));
        holder.visitorClubName.setText(game.getVisitorTeam());
        logo = game.getVisitorClubLogo();
        if(logo != null) holder.visitorClubLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
        holder.visitorClubScore.setText(String.valueOf(game.getVisitorTeamScore()));
    }

    @Override
    public int getItemCount() {
        return mGames.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        int position;
        TextView tournamentInfo;
        TextView gameDate;
        TextView homeClubName;
        ImageView homeClubLogo;
        TextView homeClubScore;
        TextView visitorClubName;
        ImageView visitorClubLogo;
        TextView visitorClubScore;
        Intent extras;



        public ViewHolder(View itemView) {
            super(itemView);
            extras = new Intent(GameAdapter.this.mContext, GameActivity.class);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(extras);
                }
            });
            tournamentInfo = (TextView) itemView.findViewById(R.id.game_tournament_info);
            gameDate = (TextView) itemView.findViewById(R.id.game_date_info);
            homeClubName = (TextView) itemView.findViewById(R.id.home_club_name);
            homeClubLogo = (ImageView) itemView.findViewById(R.id.home_club_logo);
            homeClubScore = (TextView) itemView.findViewById(R.id.home_team_score);
            visitorClubName = (TextView) itemView.findViewById(R.id.visitor_club_name);
            visitorClubLogo = (ImageView) itemView.findViewById(R.id.visitor_club_logo);
            visitorClubScore = (TextView) itemView.findViewById(R.id.visitor_team_score);

        }


    }
}
