package edu.gmu.lsaranga.quickbuy;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gmu.lsaranga.quickbuy.fragments.CartFragment;
import edu.gmu.lsaranga.quickbuy.fragments.HomeFragment;
import edu.gmu.lsaranga.quickbuy.fragments.NotificationsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ArrayList<String> data = new ArrayList<String>();

    private CartViewModel mCartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

//        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        final CartListAdapter adapter = new CartListAdapter(this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        mCartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
//
//        mCartViewModel.getAllItems().observe(this, new Observer<List<CartItem>>() {
//            @Override
//            public void onChanged(@Nullable final List<CartItem> words) {
//                // Update the cached copy of the words in the adapter.
//                adapter.setCartItems(words);
//            }
//        });
//
//        ListView lv = (ListView) findViewById(R.id.listview);
//        generateListContent();
//        lv.setAdapter(new MyListAdaper(this, R.layout.list_item, data));

        loadFragment(new HomeFragment());
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private void generateListContent() {
        data.addAll(Arrays.asList("Strawberries", "Peaches", "Apples", "Onions", "Tomatoes"));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_cart:
                fragment = new CartFragment();
                break;
            case R.id.navigation_notifications:
                fragment = new NotificationsFragment();
                break;
        }
        return loadFragment(fragment);
    }

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CartItem word = new CartItem(data.get(position));
                    mCartViewModel.insert(word);
                }
            });
            mainViewholder.title.setText(getItem(position));

            return convertView;
        }
    }
    public class ViewHolder {

        ImageView thumbnail;
        TextView title;
        Button button;
    }

}
