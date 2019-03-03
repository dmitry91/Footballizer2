package com.dmitry.footballizer.repositorys;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dmitry.footballizer.db.App;
import com.dmitry.footballizer.db.AppDatabase;
import com.dmitry.footballizer.models.StandingsList;
import com.dmitry.footballizer.retrofit.RetrofitService;
import com.dmitry.footballizer.ui.interfaces.PresenterStandings;
import com.dmitry.footballizer.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StandingRepository implements PresenterStandings.Repository {

    private Context context;
    private AppDatabase db = App.getInstance().getDatabase();
    private static StandingsList standingsListCashes  = new StandingsList();

    public StandingRepository(Activity mView) {
        context = mView;
    }

    @Override
    public void loadStandings(final int id, final PresenterStandings.OnFinishedListener onFinishedListener) {
        Call<StandingsList> mCall = RetrofitService.getInstance().getStandings(id);

        if (Utils.isNetworkAvailable(context)) {
            mCall.enqueue(new Callback<StandingsList>() {
                @Override
                public void onResponse(Call<StandingsList> call, @NonNull Response<StandingsList> response) {
                    final StandingsList standingsList = response.body();
                    standingsList.setId(id);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int rows = db.StandingsListDao().update(standingsList);
                            if (rows == 0) {
                                db.StandingsListDao().insert(standingsList);
                            }
                        }
                    }).start();

                    onFinishedListener.onFinished(standingsList);
                }

                @Override
                public void onFailure(Call<StandingsList> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        }else {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (db.StandingsListDao().getById(id).size() > 0){
                        standingsListCashes = db.StandingsListDao().getById(id).get(0);
                    }
                    else {
                        standingsListCashes  = new StandingsList();
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                onFinishedListener.onFinished(standingsListCashes);
        }

    }
}
