package edu.gmu.lsaranga.quickbuy;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartRepository mRepository;

    private LiveData<List<CartItem>> mAllitems;

    public CartViewModel (Application application) {
        super(application);
        mRepository = new CartRepository(application);
        mAllitems = mRepository.getAllItems();
    }

    public LiveData<List<CartItem>> getAllItems() { return mAllitems; }

    public void insert(CartItem word) { mRepository.insert(word); }

    public void clear() {
        mRepository.deleteAll();
    }
}
