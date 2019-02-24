package com.dmitry.footballizer.models

import com.google.gson.annotations.SerializedName


class CompetitionList {
    @SerializedName("competitions")
    var competitions:List<Competition>?=null
}