package edu.gmu.lsaranga.quickbuy.fragments;

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

import java.util.ArrayList;

import edu.gmu.lsaranga.quickbuy.CartItem;
import edu.gmu.lsaranga.quickbuy.CartViewModel;
import edu.gmu.lsaranga.quickbuy.R;
import edu.gmu.lsaranga.quickbuy.ImageCard;
import edu.gmu.lsaranga.quickbuy.adapters.LogoImageAdapter;
import edu.gmu.lsaranga.quickbuy.eventlisteners.OnItemClickListener;

public class HomeFragment extends Fragment {

    String logo_titles[] = {"Wegmans","Harris Teeter","Giant","Safeway","Whole Foods"};
    int  logo_images[] = {R.drawable.logo_wegmans, R.drawable.logo_harristeeter, R.drawable.logo_giant, R.drawable.logo_safeway, R.drawable.logo_wholefoods};
    ArrayList<ImageCard> logoItems = new ArrayList<>();

    String topbuy_titles[] = {"Mangoes","Apples","Grapes","Peaches","Watermelons"};
    int  topbuy_images[] = {R.drawable.fruit_mango,R.drawable.fruit_apple,R.drawable.fruit_grapes,R.drawable.fruit_peaches,R.drawable.fruit_watermelon};
    ArrayList<ImageCard> fruitItems = new ArrayList<>();

    private CartViewModel mCartViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logoItems.clear();
        for(int i = 0; i < logo_images.length; i++){
            ImageCard item = new ImageCard();
            item.setImageResourceId(logo_images[i]);
            logoItems.add(item);
        }

        fruitItems.clear();
        for(int i = 0; i < topbuy_images.length; i++){
            ImageCard item = new ImageCard();
            item.setImageResourceId(topbuy_images[i]);
            item.setImageCaption(topbuy_titles[i]);
            fruitItems.add(item);
        }

        mCartViewModel = ViewModelProviders.of(this).get(CartViewModel .class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView storeView = view.findViewById(R.id.home_fragment_stores_view_list);
        storeView.setHasFixedSize(true);
        LinearLayoutManager svLayoutManager = new LinearLayoutManager(getActivity());
        svLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (logoItems.size() > 0) {
            storeView.setAdapter(new LogoImageAdapter(logoItems, null));
        }
        storeView.setLayoutManager(svLayoutManager);

        RecyclerView topbuysView = view.findViewById(R.id.home_fragment_topbuys_list);
        topbuysView.setHasFixedSize(true);
        LinearLayoutManager tbvLayoutManager = new LinearLayoutManager(getActivity());
        tbvLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (fruitItems.size() > 0) {
            topbuysView.setAdapter(new LogoImageAdapter(fruitItems, new OnItemClickListener() {
                @Override
                public void onItemClick(ImageCard imageCard) {
                    mCartViewModel.insert(new CartItem(imageCard.getImageCaption()));
                }
            }));
        }
        topbuysView.setLayoutManager(tbvLayoutManager);

        return view;
    }

}
