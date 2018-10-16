package edu.gmu.lsaranga.quickbuy;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CartRepository {

    private CartDao mCartDao;
    private LiveData<List<CartItem>> mAllItems;

    CartRepository(Application application) {
        QuickBuyDatabase db = QuickBuyDatabase.getDatabase(application);
        mCartDao = db.cartDao();
        mAllItems = mCartDao.getAllItems();
    }

    LiveData<List<CartItem>> getAllItems() {
        return mAllItems;
    }


    public void insert (CartItem item) {
        new insertAsyncTask(mCartDao).execute(item);
    }

    private static class insertAsyncTask extends AsyncTask<CartItem, Void, Void> {

        private CartDao mAsyncTaskDao;

        insertAsyncTask(CartDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CartItem... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
