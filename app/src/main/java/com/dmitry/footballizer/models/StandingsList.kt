package com.dmitry.footballizer.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.support.annotation.NonNull
import com.dmitry.footballizer.models.converter.StandingsConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "standingslist")
class StandingsList{

    @NonNull
    @PrimaryKey
    var id:Int?=null;

    @TypeConverters(StandingsConverter::class)
    @SerializedName("standings")
    var standings:List<Standings>?=null;

    class Standings{
        @SerializedName("type")
        var type:String?=null;
        @SerializedName("group")
        var group:String?=null;
        @SerializedName("table")
        var table:List<Table>?=null;
    }

    class Table{
        @SerializedName("position")
        var position:String?=null;
        @SerializedName("team")
        var team:Team?=null;
        class Team{
            @SerializedName("id")
            var id:Int?=null;
            @SerializedName("name")
            var name:String?=null;
        }
    }
}