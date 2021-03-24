package com.example.android.vegetablemandi.model.RoomDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CartRepository {

    private CartDao cartDao;
    private LiveData<List<CartEntity>> allCartItem;
    private int quantity;

    public CartRepository(Application application) {
        CartDatabase cartDatabase = CartDatabase.getInstance(application);
        cartDao = cartDatabase.cartDao();
        allCartItem = cartDao.getAllItem();
    }

    public void insert(CartEntity cartEntity) {
        new InsertItemAsyncTask(cartDao).execute(cartEntity);
    }

    public void delete(CartEntity cartEntity) {
        new DeleteItemAsyncTask(cartDao).execute(cartEntity);
    }

    public void update(CartEntity cartEntity) {
        new UpdateItemAsyncTask(cartDao).execute(cartEntity);
    }

    public void deleteAllItems() {
        new DeleteAllItemAsyncTask(cartDao).execute();
    }

    public int updateQuantity(String name) {
        new UpdateQuantityItemAsyncTask(cartDao).execute(name);
       // cartDao.updateQuantity(name);
        return quantity;
    }

    public LiveData<CartEntity> getItemByName(String name) {
        return cartDao.getItemByName(name);
    }

    public List<CartEntity> updateItem(String name) {
       return cartDao.getItemById(name);
    }

    public LiveData<List<CartEntity>> getAllCartItem() {
        return allCartItem;
    }


    private static class InsertItemAsyncTask extends AsyncTask<CartEntity, Void, Void> {
        private CartDao cartDao;

        public InsertItemAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartEntity... cartEntities) {
            cartDao.insert(cartEntities[0]);
            return null;
        }
    }

    private static class DeleteItemAsyncTask extends AsyncTask<CartEntity,Void,Void> {
        private CartDao cartDao;

        public DeleteItemAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartEntity... cartEntities) {
            cartDao.delete(cartEntities[0]);
            return null;
        }
    }

    private static class UpdateItemAsyncTask extends AsyncTask<CartEntity, Void, Void> {
        private CartDao cartDao;

        public UpdateItemAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartEntity... cartEntities) {
            cartDao.update(cartEntities[0]);
            return null;
        }
    }

    private static class DeleteAllItemAsyncTask extends AsyncTask<CartEntity, Void, Void> {
        private CartDao cartDao;

        public DeleteAllItemAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartEntity... cartEntities) {
            cartDao.deleteAll();
            return null;
        }
    }

    private static class UpdateQuantityItemAsyncTask extends AsyncTask<String, Void, Integer> {
        private CartDao cartDao;

        public UpdateQuantityItemAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Integer doInBackground(String... strings) {
            cartDao.updateQuantity(strings[0]);
            return null;
        }
    }
}
