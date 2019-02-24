package com.dmitry.footballizer.retrofit;

import com.dmitry.footballizer.models.Competition;
import com.dmitry.footballizer.models.CompetitionList;
import com.dmitry.footballizer.models.StandingsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    int[] freeCompetitions = new int[]{ 2000,2001,2002,2003,2013,2014,2015,2016,2017,2018,2019,2021 };

    @GET("/v2/competitions/{id}")
    Call<Competition> getCompetition(@Path("id") int id);

    @GET("/v2/competitions")
    Call<CompetitionList> getAllCompetitions();

    @GET("/v2/competitions/{id}/standings")
    Call<StandingsList> getStandings(@Path("id") int id);

}
