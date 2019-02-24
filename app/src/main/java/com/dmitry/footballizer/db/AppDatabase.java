package com.dmitry.footballizer.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.dmitry.footballizer.models.Competition;

@Database(entities = { Competition.class }, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CompetitionDao CompetitionDao();
}