package com.example.bikes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.room.Database;

import com.example.bikes.DataBaseModule.RoomDB;
import com.example.bikes.recyclerViewClass.ItemBike;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String currency;
    public static List<ItemBike> listItemBikesStore=new ArrayList<>();
    public static List<ItemBike> listItemBikesBasket=new ArrayList<>();
    public static RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        database= RoomDB.getInstance(this);






        currency="zl";


    }
    public static void addToBDStore(){

    }
    public static void selectAllFromDBStore(){
        //getting data from server metod TO-DO
        //server getter imitator
        listItemBikesStore.clear();
        listItemBikesStore.add(new ItemBike(0,"SuperFast","GHT-45",50,"blue","KROSS"));
        listItemBikesStore.add(new ItemBike(1,"Cress","GHT-750",59,"green","Bike"));
        listItemBikesStore.add(new ItemBike(2,"SLOW","RT-6",40,"blue","KROSS"));
        listItemBikesStore.add(new ItemBike(3,"fast","RT-9",42000,"blue","KROSS"));
        listItemBikesStore.add(new ItemBike(4,"SuperFast","GROM",5000,"blue","KROSS"));
        listItemBikesStore.add(new ItemBike(5,"SuperFast","LIGHT",80000,"red","samsung"));
        
    }

}