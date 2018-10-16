package edu.gmu.lsaranga.quickbuy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "cart_table")
public class CartItem {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "item")
    private String item;

    public CartItem(String item) {this.item = item;}

    public String getItem(){return this.item;}

}
