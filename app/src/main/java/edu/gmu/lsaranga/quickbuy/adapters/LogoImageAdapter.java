package edu.gmu.lsaranga.quickbuy.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.gmu.lsaranga.quickbuy.ImageCard;
import edu.gmu.lsaranga.quickbuy.R;
import edu.gmu.lsaranga.quickbuy.eventlisteners.OnItemClickListener;
import edu.gmu.lsaranga.quickbuy.views.ImageCardHolder;

public class LogoImageAdapter extends RecyclerView.Adapter<ImageCardHolder> {
    private ArrayList<ImageCard> list;
    private OnItemClickListener listener;

    public LogoImageAdapter(ArrayList<ImageCard> Data, OnItemClickListener listener) {
        list = Data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home_image_card, parent, false);
        return new ImageCardHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageCardHolder holder, int position) {
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}