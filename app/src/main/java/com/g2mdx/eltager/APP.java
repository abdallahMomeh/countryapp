package com.g2mdx.eltager;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;


import com.g2mdx.basicproject.util.LocalHelper;
import com.g2mdx.eltager.model.source.db.impl.MyDatabase;

public class APP extends Application {

    private static final String DATABASE_NAME = "MyDatabase";
    private static final String PREFERENCES = "RoomDemo.preferences";
    private static final String KEY_FORCE_UPDATE = "force_update";

    public static Context context;
    public static APP INSTANCE;
    MyDatabase database;

    public static APP get() {
        return INSTANCE;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        // create database
        database = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                // .addMigrations(MyDatabase.MIGRATION_1_2)
                .build();

        INSTANCE = this;
    }

    public MyDatabase getDB() {
        return database;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalHelper.onAttach(base, "ar"));
    }

}