package edu.gmu.lsaranga.quickbuy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.gmu.lsaranga.quickbuy.R;
import edu.gmu.lsaranga.quickbuy.adapters.LogoImage;
import edu.gmu.lsaranga.quickbuy.adapters.LogoImageAdapter;

public class HomeFragment extends Fragment {

    String logo_titles[] = {"Wegmans","Harris Teeter","Giant","Safeway","Whole Foods"};
    int  logo_images[] = {R.drawable.logo_wegmans, R.drawable.logo_harristeeter, R.drawable.logo_giant, R.drawable.logo_safeway, R.drawable.logo_wholefoods};
    ArrayList<LogoImage> listitems = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listitems.clear();
        for(int i = 0; i < logo_images.length; i++){
            LogoImage item = new LogoImage();
            item.setImageResourceId(logo_images[i]);
            listitems.add(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView storeView = view.findViewById(R.id.fragment_home_store_view);
        storeView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (listitems.size() > 0) {
            storeView.setAdapter(new LogoImageAdapter(listitems));
        }
        storeView.setLayoutManager(MyLayoutManager);

        return view;
    }

}
