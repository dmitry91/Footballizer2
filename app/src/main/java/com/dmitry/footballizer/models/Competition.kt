package com.dmitry.footballizer.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
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
    @Ignore
    @SerializedName("area")
    var area:Area?=null;
    @Ignore
    @SerializedName("currentSeason")
    var currentSeason:CurrentSeason?=null;

    class Area {
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