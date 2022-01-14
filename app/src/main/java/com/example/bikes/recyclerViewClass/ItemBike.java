package com.example.bikes.recyclerViewClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_basket_items")
public class ItemBike implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private int price;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "brand")
    private String brand;


//    private int id;
//    private String model;
//    private String name;
//    private int price;
//    private String color;
//    private String brand;

    public ItemBike() {
    }

    public ItemBike(int id,String model, String name, int price, String color, String brand) {
        this.id=id;
        this.model = model;
        this.name = name;
        this.price = price;
        this.color = color;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
