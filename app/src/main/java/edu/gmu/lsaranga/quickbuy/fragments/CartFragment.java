package edu.gmu.lsaranga.quickbuy.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import edu.gmu.lsaranga.quickbuy.CartItem;
import edu.gmu.lsaranga.quickbuy.CartViewModel;
import edu.gmu.lsaranga.quickbuy.R;
import edu.gmu.lsaranga.quickbuy.adapters.CartListAdapter;

public class CartFragment extends Fragment {

    private CartViewModel mCartViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, null);

        RecyclerView recyclerView = view.findViewById(R.id.cart_list_view);
        final CartListAdapter adapter = new CartListAdapter(view.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mCartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        mCartViewModel.getAllItems().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(@Nullable final List<CartItem> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setCartItems(words);
            }
        });

        Button clearBtn = view.findViewById(R.id.fragment_cart_clear_btn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCartViewModel.clear();
            }
        });

        return view;
    }
}
