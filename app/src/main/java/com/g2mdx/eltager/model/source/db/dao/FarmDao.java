package com.g2mdx.eltager.model.source.db.dao;

import com.g2mdx.eltager.model.dto.Farm;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class FarmDao {

    @Query("select * from farm")
    abstract LiveData<List<Farm>> getFarms();

}
