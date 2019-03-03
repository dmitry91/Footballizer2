package com.dmitry.footballizer.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dmitry.footballizer.models.StandingsList;

import java.util.List;

@Dao
public interface StandingsListDao {
    @Insert
    void insert(StandingsList standingsList);

    @Update
    int update(StandingsList standingsList);

    @Delete
    void delete(StandingsList standingsList);

    @Query("SELECT * FROM standingslist WHERE id = :id ")
    List<StandingsList> getById(int id);
}
