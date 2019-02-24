package com.dmitry.footballizer.repositorys;

import android.support.annotation.NonNull;

import com.dmitry.footballizer.models.StandingsList;
import com.dmitry.footballizer.retrofit.RetrofitService;
import com.dmitry.footballizer.ui.interfaces.PresenterStandings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StandingRepository implements PresenterStandings.Repository {

    @Override
    public void loadStandings(final int id, final PresenterStandings.OnFinishedListener onFinishedListener) {
        Call<StandingsList> mCall = RetrofitService.getInstance().getStandings(id);
        mCall.enqueue(new Callback<StandingsList>() {
            @Override
            public void onResponse(Call<StandingsList> call, @NonNull Response<StandingsList> response) {
                onFinishedListener.onFinished( response.body());

            }
            @Override
            public void onFailure(Call<StandingsList> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
