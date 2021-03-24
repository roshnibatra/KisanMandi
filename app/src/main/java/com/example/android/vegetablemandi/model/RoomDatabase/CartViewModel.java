package com.example.android.vegetablemandi.model.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class CartViewModel extends AndroidViewModel {

    private CartRepository cartRepository;
    private LiveData<List<CartEntity>> allItems;


    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepository = new CartRepository(application);
        allItems =cartRepository.getAllCartItem();
    }

    public void delete(CartEntity cartEntity) {
        cartRepository.delete(cartEntity);
    }

    public void update(CartEntity cartEntity) {
        cartRepository.update(cartEntity);
    }


    public void updateQuantity(String name) {
        cartRepository.updateQuantity(name);
    }

    public void decrementQuantity(String name) {
        cartRepository.decrementQuantity(name);
    }

    public void insert(CartEntity cartEntity) {
        cartRepository.insert(cartEntity);
    }

    public LiveData<CartEntity> getItemByName(String name) {
        return cartRepository.getItemByName(name);
    }
    public void deleteAll() {
        cartRepository.deleteAllItems();
    }


    public LiveData<List<CartEntity>> getAllItems() {
        return allItems;
    }
}
