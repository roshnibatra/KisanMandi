package com.example.android.vegetablemandi.model.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {CartEntity.class},version =3)
@TypeConverters({DateConverter.class})
public abstract class CartDatabase extends RoomDatabase {
    private static volatile CartDatabase INSTANCE;

    public abstract CartDao cartDao();

    public static synchronized CartDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CartDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(),
                                    CartDatabase.class, "cart_table")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
            return INSTANCE;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
           // new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private CartDao noteDao;

        public PopulateDbAsyncTask(CartDatabase noteDatabase) {
            noteDao = noteDatabase.cartDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {


            return null;
        }
    }
}

