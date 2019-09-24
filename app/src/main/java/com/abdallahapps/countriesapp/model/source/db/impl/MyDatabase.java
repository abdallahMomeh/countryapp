package com.abdallahapps.countriesapp.model.source.db.impl;

import androidx.room.RoomDatabase;


import com.abdallahapps.countriesapp.model.source.db.dao.FarmDao;

//@Database(entities = {} , version = 1 , exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract  FarmDao farmDao();

}
