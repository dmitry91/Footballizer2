package com.dmitry.footballizer.repositorys;

import android.support.annotation.NonNull;

import com.dmitry.footballizer.models.Competition;
import com.dmitry.footballizer.retrofit.RetrofitService;
import com.dmitry.footballizer.ui.interfaces.PresenterCompetitionDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompetitionDetailsRepository implements PresenterCompetitionDetail.Repository {

    @Override
    public void loadCompetition(final PresenterCompetitionDetail.OnFinishedListener onFinishedListener, int id) {
        Call<Competition> mCall = RetrofitService.getInstance().getCompetition(id);
        mCall.enqueue(new Callback<Competition>() {
            @Override
            public void onResponse(Call<Competition> call, @NonNull Response<Competition> response) {
            Competition competition = response.body();
                onFinishedListener.onFinished(competition);

            }
            @Override
            public void onFailure(Call<Competition> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
