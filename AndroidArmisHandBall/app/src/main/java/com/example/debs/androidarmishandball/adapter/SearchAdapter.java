package com.example.debs.androidarmishandball.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debs.androidarmishandball.R;
import com.example.debs.androidarmishandball.activity.ClubActivity;
import com.example.debs.androidarmishandball.activity.TournamentActivity;
import com.example.debs.androidarmishandball.restclient.dto.SimpleContent;



public class SearchAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private SimpleContent[] mResults;


    public SearchAdapter(Context mContext, SimpleContent[] mResults) {
        this.mContext = mContext;
        this.mResults = mResults;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_item, parent, false);
        return new ViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder view, int position) {

            ViewHolder holder = (ViewHolder)view;
            SimpleContent result =  mResults[position];
            holder.position = position;
            holder.resultName.setText(result.getName());
            if (result.getType() == SimpleContent.SearchableType.Tournament) {
                holder.extras = new Intent(mContext, TournamentActivity.class);
                holder.extras.putExtra("tournament", result);
            }else if(result.getType() == SimpleContent.SearchableType.Club) {
                holder.extras = new Intent(mContext, ClubActivity.class);
                holder.extras.putExtra("club", result);
            }
            byte[] image = result.getImage();
            if (image != null) {
                holder.resultImage.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
            }else{
                if (result.getType() == SimpleContent.SearchableType.Tournament) {
                    holder.resultImage.setImageResource(R.drawable.tournament_black_icon);
                }else if(result.getType() == SimpleContent.SearchableType.Club) {
                    holder.resultImage.setImageResource(R.drawable.club_default_icon);
                }else if(result.getType() == SimpleContent.SearchableType.Athlete) {
                    holder.resultImage.setImageResource(R.drawable.athlete_black_icon);
                }
            }

    }

    public void changeViewContent (SimpleContent[] newContent){
        mResults = newContent;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mResults.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        int position;
        TextView resultName;
        TextView resultType;
        ImageView resultImage;
        Intent extras;

        public ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(extras);
                }
            });
            resultName = (TextView) itemView.findViewById(R.id.result_name);
            resultImage = (ImageView) itemView.findViewById(R.id.result_image);


        }
    }

}
