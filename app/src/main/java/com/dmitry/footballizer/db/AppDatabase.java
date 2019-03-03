package com.dmitry.footballizer.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.dmitry.footballizer.models.Competition;
import com.dmitry.footballizer.models.StandingsList;

@Database(entities = { Competition.class, StandingsList.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CompetitionDao CompetitionDao();
    public abstract StandingsListDao StandingsListDao();
}