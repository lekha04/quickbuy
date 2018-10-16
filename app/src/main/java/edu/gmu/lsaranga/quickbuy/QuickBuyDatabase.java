package edu.gmu.lsaranga.quickbuy;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {CartItem.class}, version = 1)
public abstract class QuickBuyDatabase extends RoomDatabase {

    public abstract CartDao cartDao();

    private static volatile QuickBuyDatabase INSTANCE;

    static QuickBuyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuickBuyDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuickBuyDatabase.class, "quickbuy_database")
//                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CartDao mDao;

        PopulateDbAsync(QuickBuyDatabase db) {
            mDao = db.cartDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            CartItem word = new CartItem("Hello");
            mDao.insert(word);
            word = new CartItem("World");
            mDao.insert(word);
            return null;
        }
    }

}
