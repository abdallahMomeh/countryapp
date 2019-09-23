package com.abdallahapps.coutoriesapp.model.source.db.impl;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.abdallahapps.coutoriesapp.model.source.db.dao.FarmDao;

//@Database(entities = {} , version = 1 , exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract  FarmDao farmDao();

}
