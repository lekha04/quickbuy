package edu.gmu.lsaranga.quickbuy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private ItemViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.cart_list_item_text);
        }
    }

    private final LayoutInflater mInflater;
    private List<CartItem> mCartItems; // Cached copy of words

    CartListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cart_list_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (mCartItems != null) {
            CartItem current = mCartItems.get(position);
            holder.wordItemView.setText(current.getItem());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    void setCartItems(List<CartItem> items){
        mCartItems = items;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mCartItems has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCartItems != null)
            return mCartItems.size();
        else return 0;
    }

}
