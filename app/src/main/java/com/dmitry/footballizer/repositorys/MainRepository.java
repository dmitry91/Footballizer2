package com.dmitry.footballizer.repositorys;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dmitry.footballizer.db.App;
import com.dmitry.footballizer.db.AppDatabase;
import com.dmitry.footballizer.models.Competition;
import com.dmitry.footballizer.models.CompetitionList;
import com.dmitry.footballizer.retrofit.API;
import com.dmitry.footballizer.retrofit.RetrofitService;
import com.dmitry.footballizer.ui.interfaces.PresenterCompetitionsMain;
import com.dmitry.footballizer.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dmitry.footballizer.retrofit.API.freeCompetitions;

public class MainRepository implements PresenterCompetitionsMain.Repository {

    private Context context;
    private AppDatabase db = App.getInstance().getDatabase();
    static ArrayList<Competition> competitionsCashes;

    public MainRepository(Activity mView) {
        context = mView;
    }

    @Override
    public void loadCompetitions(final PresenterCompetitionsMain.OnFinishedListener onFinishedListener) {

        if (Utils.isNetworkAvailable(context)) {
            Call<CompetitionList> mCall = RetrofitService.getInstance().getAllCompetitions();
            mCall.enqueue(new Callback<CompetitionList>() {
                @Override
                public void onResponse(Call<CompetitionList> call, @NonNull Response<CompetitionList> response) {

                    ArrayList<Competition> res = (ArrayList<Competition>) response.body().getCompetitions();
                    res = (filterCompetitions(res));

                    final ArrayList<Competition> competitionsCashes = res;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int rows = db.CompetitionDao().update(competitionsCashes);
                            if (rows == 0) {
                                db.CompetitionDao().insert(competitionsCashes);
                            }

                        }
                    }).start();

                    onFinishedListener.onFinished(res);
                }

                @Override
                public void onFailure(Call<CompetitionList> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        }
        else {
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
            onFinishedListener.onFinished(competitionsCashes);
        }

    }

    //we take events available on a free key
    private ArrayList<Competition> filterCompetitions(ArrayList<Competition> competitions){
        ArrayList<Competition> result = new ArrayList<>();
        for (Competition c : competitions) {
            for(int i = 0; i < freeCompetitions.length ; i++){
                if (c.getId() == API.freeCompetitions[i]){
                    result.add(c);
                }
            }
        }
        return result;
    }

}

