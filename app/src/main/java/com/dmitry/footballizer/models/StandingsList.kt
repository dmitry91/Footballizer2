package com.dmitry.footballizer.models

import com.google.gson.annotations.SerializedName

class StandingsList{

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