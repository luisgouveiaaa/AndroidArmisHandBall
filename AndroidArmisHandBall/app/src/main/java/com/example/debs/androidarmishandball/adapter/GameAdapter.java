package com.example.debs.androidarmishandball.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debs.androidarmishandball.Game;
import com.example.debs.androidarmishandball.R;

import java.util.List;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{

    private Context mContext;
    private List<Game> mGames;

    public GameAdapter(Context context, List<Game> games){
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game game = mGames.get(position);

        holder.gameDate.setText(game.getDate().toString());
        holder.tournamentInfo.setText(game.getMatchDay().getEdition().getTournament().getName());
        holder.homeClubName.setText(game.getHomeTeam().getClub().getName());
        byte[] logo = game.getHomeTeam().getClub().getLogo();
        holder.homeClubLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
        holder.homeClubScore.setText(game.getHomeTeamScore());
        holder.visitorClubName.setText(game.getVisitorTeam().getClub().getName());
        logo = game.getVisitorTeam().getClub().getLogo();
        holder.visitorClubLogo.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
        holder.visitorClubScore.setText(game.getVisitorTeamScore());
    }

    @Override
    public int getItemCount() {
        return 0;
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



        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //initiate activity;
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
