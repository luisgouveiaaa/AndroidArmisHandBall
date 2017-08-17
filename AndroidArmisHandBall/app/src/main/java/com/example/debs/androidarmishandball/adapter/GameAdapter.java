package com.example.debs.androidarmishandball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        int position;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //initiate activity;
                }
            });

        }

        /*Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        imageview.setImageBitmap(bmp);*/
    }
}
