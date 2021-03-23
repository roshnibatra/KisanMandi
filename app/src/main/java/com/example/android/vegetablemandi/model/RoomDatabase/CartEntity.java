package com.example.android.vegetablemandi.model.RoomDatabase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;

@Entity(tableName = "cart_table")
public class CartEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "vegetable_name")
    public String name;

    @ColumnInfo(name = "vegetable_price")
    public double price;

    @ColumnInfo(name = "vegetable_image")
    public String logo;

    @ColumnInfo(name = "quantity")
    public int actual_quantity;

    @ColumnInfo(name = "min_quantity")
    public int minimum_quantity;

    @ColumnInfo(name = "timeStamp")
    public Date timeStamp;

    @ColumnInfo(name = "unit")
    public String unit;

    @ColumnInfo(name = "icon")
    public String icon;


    public CartEntity(String name, int price, String logo, int cart_quantity, int minimum_quantity) {
        this.name = name;
        this.price = price;
        this.logo = logo;
        this.actual_quantity = cart_quantity;
        this.minimum_quantity = minimum_quantity;

    }

    public CartEntity( String name, double price, String logo, int actual_quantity, int minimum_quantity, String unit, String icon) {
        this.name = name;
        this.price = price;
        this.logo = logo;
        this.actual_quantity = actual_quantity;
        this.minimum_quantity = minimum_quantity;
     //   this.timeStamp = timeStamp;
        this.unit = unit;
        this.icon = icon;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getCart_quantity() {
        return actual_quantity;
    }

    public void setCart_quantity(int cart_quantity) {
        this.actual_quantity = cart_quantity;
    }

    public int getMinimum_quantity() {
        return minimum_quantity;
    }

    public void setMinimum_quantity(int minimum_quantity) {
        this.minimum_quantity = minimum_quantity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getActual_quantity() {
        return actual_quantity;
    }

    public void setActual_quantity(int actual_quantity) {
        this.actual_quantity = actual_quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date){
        return date == null ? null : date.getTime();
    }
}
