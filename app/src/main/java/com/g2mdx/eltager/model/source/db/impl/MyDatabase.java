package com.g2mdx.eltager.model.source.db.impl;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.g2mdx.eltager.model.dto.User;

@Database(entities = {User.class} , version = 1 , exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

}
