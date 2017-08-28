package com.example.debs.androidarmishandball.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debs.androidarmishandball.Game;
import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.activity.GameActivity;

import java.util.List;

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

        holder.gameDate.setText(game.getDate());
        holder.extras.putCharSequence("date", game.getDate());
        holder.tournamentInfo.setText(game.getMatchDay());
        holder.extras.putCharSequence("matchDay", game.getMatchDay());
        holder.homeClubName.setText(game.getHomeTeam());
        holder.extras.putCharSequence("homeTeam", game.getHomeTeam());
        byte[] logo = game.getHomeClubLogo();
        holder.extras.putByteArray("homeClubLogo", logo);
        if(logo != null) holder.homeClubLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
        holder.homeClubScore.setText(String.valueOf(game.getHomeTeamScore()));
        holder.extras.putByte("homeTeamScore", game.getHomeTeamScore());
        holder.visitorClubName.setText(game.getVisitorTeam());
        holder.extras.putCharSequence("visitorTeam", game.getVisitorTeam());
        logo = game.getVisitorClubLogo();
        holder.extras.putByteArray("homeVisitorLogo", logo);
        if(logo != null) holder.visitorClubLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
        holder.visitorClubScore.setText(String.valueOf(game.getVisitorTeamScore()));
        holder.extras.putByte("visitorTeamScore", game.getVisitorTeamScore());
        holder.extras.putInt("GamePk", game.getPk());
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
        Bundle extras;



        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new GameActivity(extras);
                }
            });
            extras = new Bundle();
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
