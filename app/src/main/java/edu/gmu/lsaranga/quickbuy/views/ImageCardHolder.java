package edu.gmu.lsaranga.quickbuy.views;

import android.support.constraint.Group;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import edu.gmu.lsaranga.quickbuy.ImageCard;
import edu.gmu.lsaranga.quickbuy.R;
import edu.gmu.lsaranga.quickbuy.eventlisteners.OnItemClickListener;

public class ImageCardHolder extends RecyclerView.ViewHolder {

    public final ImageView coverImageView;
    public final TextView coverImageCaption;
    public final FloatingActionButton coverImageButton;

    public ImageCardHolder(View v){
        super(v);
        coverImageView= v.findViewById(R.id.coverImageView);
        coverImageCaption = v.findViewById(R.id.coverImageCaption);
        coverImageButton = v.findViewById(R.id.coverImageButton);
    }

    public void bind(final ImageCard imageCard, final OnItemClickListener listener) {
        coverImageView.setImageResource(imageCard.getImageResourceId());
        coverImageCaption.setText(imageCard.getImageCaption());
        coverImageCaption.setVisibility(imageCard.captionVisibility());
        coverImageButton.setVisibility(imageCard.captionVisibility());

        coverImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(imageCard);
            }
        });
    }
}
