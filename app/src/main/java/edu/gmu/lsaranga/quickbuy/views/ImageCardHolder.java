package edu.gmu.lsaranga.quickbuy.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.gmu.lsaranga.quickbuy.R;

public class ImageCardHolder extends RecyclerView.ViewHolder{

    public ImageView coverImageView;

    public ImageCardHolder(View v){
        super(v);
        coverImageView= v.findViewById(R.id.coverImageView);
    }
}
