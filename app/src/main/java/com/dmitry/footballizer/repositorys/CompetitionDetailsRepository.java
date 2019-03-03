package com.dmitry.footballizer.repositorys;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.dmitry.footballizer.db.App;
import com.dmitry.footballizer.db.AppDatabase;
import com.dmitry.footballizer.models.Competition;
import com.dmitry.footballizer.retrofit.RetrofitService;
import com.dmitry.footballizer.ui.interfaces.PresenterCompetitionDetail;
import com.dmitry.footballizer.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompetitionDetailsRepository implements PresenterCompetitionDetail.Repository {

    private Context context;
    private AppDatabase db = App.getInstance().getDatabase();
    static ArrayList<Competition> competitionsCashes = new ArrayList<>();

    public CompetitionDetailsRepository(Fragment mView) {
        context = mView.getContext();
    }

    @Override
    public void loadCompetition(final PresenterCompetitionDetail.OnFinishedListener onFinishedListener, int id) {
        Call<Competition> mCall = RetrofitService.getInstance().getCompetition(id);

        if (Utils.isNetworkAvailable(context)) {
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
        }else {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    competitionsCashes = (ArrayList<Competition>) db.CompetitionDao().getAll();
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Competition c : competitionsCashes){
                if(c.getId() !=null && id == c.getId()){
                    onFinishedListener.onFinished(c);
                }
            }
        }
    }
}
