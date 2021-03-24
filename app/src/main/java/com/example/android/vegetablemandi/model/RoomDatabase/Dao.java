package com.example.android.vegetablemandi.model.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
 interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartEntity cartEntity);

    @Update
    void update(CartEntity cartEntity);

    @Delete
    void delete(CartEntity cartEntity);

   @Query("DELETE FROM cart_table")
   void deleteAll();

    @Query("SELECT * FROM cart_table ORDER BY timeStamp")
    LiveData<List<CartEntity>> getAllItem();

    @Query("SELECT * FROM cart_table WHERE vegetable_name = :name limit 1")
    LiveData<CartEntity> getItemByName(String name);

    @Query("UPDATE cart_table SET quantity = quantity + 1 WHERE vegetable_name = :name")
    int updateQuantity(String name);

    @Query("SELECT * from cart_table WHERE vegetable_name= :name")
    List<CartEntity> getItemById(String name);


}



