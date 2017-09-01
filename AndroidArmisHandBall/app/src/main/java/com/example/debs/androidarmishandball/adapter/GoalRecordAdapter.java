package com.example.debs.androidarmishandball.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debs.androidarmishandball.GoalRecord;
import com.example.debs.androidarmishandball.R;

/**
 * Created by Luis Gouveia on 28/08/2017.
 */

public class GoalRecordAdapter extends RecyclerView.Adapter<GoalRecordAdapter.ViewHolder>{

    private Context mContext;
    private GoalRecord[] mGoals;

    public GoalRecordAdapter(Context mContext, GoalRecord[] goals) {
        this.mContext = mContext;
        this.mGoals = goals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.athlete_game_score_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoalRecord goal = mGoals[position];
        holder.atheleName.setText(goal.getAthleteName());
        holder.goals.setText(String.valueOf(goal.getCount()));
        byte[] image = goal.getAthletePhoto();
        if(image != null){
            holder.athletePhoto.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
        }
    }

    @Override
    public int getItemCount() {
        return mGoals.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        int position;
        TextView atheleName;
        TextView goals;
        ImageView athletePhoto;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //initiate activity;
                }
            });

            atheleName = (TextView) itemView.findViewById(R.id.athlete_name);
            goals = (TextView) itemView.findViewById(R.id.athlete_game_score);
            athletePhoto = (ImageView) itemView.findViewById(R.id.athlete_photo);
        }
    }
}

