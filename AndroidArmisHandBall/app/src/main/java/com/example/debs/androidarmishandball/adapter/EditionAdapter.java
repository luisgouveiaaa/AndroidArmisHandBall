package com.example.debs.androidarmishandball.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.restclient.dto.Edition;

public class EditionAdapter extends RecyclerView.Adapter<EditionAdapter.ViewHolder> {

    private Context mContext;
    private Edition[] mResults;

    public EditionAdapter(Context mContext, Edition[] mResults) {
        this.mContext = mContext;
        this.mResults = mResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.edition_list_item, parent, false);
        return new EditionAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Edition result = mResults[position];
        holder.resultSeason.setText(result.getSeason());
    }

    @Override
    public int getItemCount() {
        return mResults.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        int position;
        TextView resultSeason;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //initiate activity;
                }
            });

            resultSeason = (TextView) itemView.findViewById(R.id.result_season);
        }
    }
}
