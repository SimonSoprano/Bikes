package com.example.bikes.DataBaseModule;

import static androidx.room.OnConflictStrategy.REPLACE;

import android.sax.TextElementListener;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bikes.recyclerViewClass.ItemBike;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE)
    void insert (ItemBike itemBike);

    @Delete
    void delete(ItemBike itemBike);

    @Delete
    void reset(List<ItemBike> itemBikes);

    @Query("UPDATE table_basket_items SET model = :sModel WHERE id = :sid")
    void update(int sid,String sModel);

    @Query("SELECT * FROM table_basket_items")
    List<ItemBike> getAll();

}
