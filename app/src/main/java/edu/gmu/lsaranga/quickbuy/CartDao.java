package edu.gmu.lsaranga.quickbuy;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.provider.BaseColumns;

import java.util.List;

@Dao
public interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartItem item);

    @Query("DELETE FROM cart_table")
    void deleteAll();

    @Query("SELECT * from cart_table ORDER BY item ASC")
    LiveData<List<CartItem>> getAllItems();

}
