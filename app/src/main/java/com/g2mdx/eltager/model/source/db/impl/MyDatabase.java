package com.g2mdx.eltager.model.source.db.impl;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.g2mdx.eltager.model.dto.Farm;
import com.g2mdx.eltager.model.dto.User;
import com.g2mdx.eltager.model.source.db.dao.FarmDao;

@Database(entities = {User.class , Farm.class} , version = 1 , exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract  FarmDao farmDao();

}
