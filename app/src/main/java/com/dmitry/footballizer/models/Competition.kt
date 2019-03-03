package com.dmitry.footballizer.models

import android.arch.persistence.room.*
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity
class Competition {

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    var id:Int?=null;
    @SerializedName("name")
    var name:String?=null;
    @Embedded
    @SerializedName("area")
    var area:Area?=null;
    @Embedded
    @SerializedName("currentSeason")
    var currentSeason:CurrentSeason?=null;

    class Area {
        @ColumnInfo(name = "area_name")
        @SerializedName("name")
        var name:String?=null;
    }

    class CurrentSeason{
        @SerializedName("startDate")
        var startDate:String?=null;
        @SerializedName("endDate")
        var endDate:String?=null;
    }
}