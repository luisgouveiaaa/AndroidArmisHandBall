package com.example.debs.androidarmishandball.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.activity.TeamActivity;
import com.example.debs.androidarmishandball.restclient.dto.Edition;
import com.example.debs.androidarmishandball.restclient.dto.SimpleContent;
import com.example.debs.androidarmishandball.restclient.dto.Team;

/**
 * Created by Luis Gouveia on 01/09/2017.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private Context mContext;
    private SimpleContent mClub;
    private Team[] mTeams;

    public TeamAdapter(Context context, Team[] teams, SimpleContent club) {
        this.mContext = context;
        this.mTeams = teams;
        this.mClub = club;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Team team = mTeams[position];
        holder.name.setText(team.getAgeRange() +" " +team.getGender());
        holder.logo.setImageResource(R.drawable.club_default_icon);
        holder.extras.putExtra("club", mClub);
        holder.extras.putExtra("team", team);
    }

    @Override
    public int getItemCount() {
        return mTeams.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        int position;
        TextView name;
        ImageView logo;
        Intent extras;

        public ViewHolder(View itemView) {
            super(itemView);
            extras = new Intent(mContext, TeamActivity.class);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(extras);
                }
            });

            name = (TextView) itemView.findViewById(R.id.result_name);
            logo = (ImageView) itemView.findViewById(R.id.result_image);
        }
    }
}
