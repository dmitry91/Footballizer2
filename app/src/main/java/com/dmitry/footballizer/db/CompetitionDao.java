package com.dmitry.footballizer.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dmitry.footballizer.models.Competition;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CompetitionDao {

    @Insert
    void insert(ArrayList<Competition> competition);

    @Update
    int update(ArrayList<Competition> competition);

    @Delete
    void delete(ArrayList<Competition> competition);

    @Query("SELECT * FROM competition")
    List<Competition> getAll();
}
