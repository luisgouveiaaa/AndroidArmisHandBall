package com.example.debs.androidarmishandball.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.activity.SetGameResultsActivity;
import com.example.debs.androidarmishandball.restclient.dto.GoalRecord;

/**
 * Created by Luis Gouveia on 07/09/2017.
 */

public class GoalRecordUpdateAdapter extends RecyclerView.Adapter<GoalRecordUpdateAdapter.ViewHolder> {
    private Activity mContext;
    private GoalRecord[] mGoals;

    public GoalRecordUpdateAdapter(Activity mContext, GoalRecord[] goals) {
        this.mContext = mContext;
        this.mGoals = goals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.athlete_set_game_score_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoalRecord goal = mGoals[position];
        holder.goalRecord = goal;
        holder.atheleName.setText(goal.getAthleteName());
        holder.goals.setText(String.valueOf(goal.getCount()));
        byte[] image = goal.getAthletePhoto();
        if (image != null) {
            holder.athletePhoto.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
        }


    }


    @Override
    public int getItemCount() {
        return mGoals.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        int position;
        GoalRecord goalRecord;
        TextView atheleName;
        TextView goals;
        ImageView athletePhoto;


        public ViewHolder(final View itemView) {
            super(itemView);

            itemView.findViewById(R.id.add_goal_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    byte newGoalNumber = Byte.parseByte(goals.getText().toString());
                    newGoalNumber++;
                    goals.setText(String.valueOf(newGoalNumber));
                    TextView teamScore = (TextView) mContext.findViewById(R.id.team_score);
                    teamScore.setText(String.valueOf(Integer.parseInt(teamScore.getText().toString())+1));
                    goalRecord.setCount(newGoalNumber);
                    updateMinusButtonVisibility();
                }
            });

            itemView.findViewById(R.id.remove_goal_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    byte newGoalNumber = Byte.parseByte(goals.getText().toString());
                    newGoalNumber--;
                    goals.setText(String.valueOf(newGoalNumber));
                    TextView teamScore = (TextView) mContext.findViewById(R.id.team_score);
                    teamScore.setText(String.valueOf(Integer.parseInt(teamScore.getText().toString())-1));
                    goalRecord.setCount(newGoalNumber);
                    updateMinusButtonVisibility();
                }
            });
            atheleName = (TextView) itemView.findViewById(R.id.athlete_name);
            goals = (TextView) itemView.findViewById(R.id.athlete_game_score);
            athletePhoto = (ImageView) itemView.findViewById(R.id.athlete_photo);

        }

        public void updateMinusButtonVisibility() {
            if (goals.getText().toString().equals("0")) {
                itemView.findViewById(R.id.remove_goal_button).setClickable(false);
            } else {
                itemView.findViewById(R.id.remove_goal_button).setClickable(true);
            }
        }
    }
}
