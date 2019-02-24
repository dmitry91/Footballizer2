package com.dmitry.footballizer.ui.interfaces;
import com.dmitry.footballizer.models.StandingsList;

import java.util.ArrayList;

public interface PresenterStandings {

    interface View {
        void showCompetitions(StandingsList noticeArrayList);
    }

    interface Presenter {
        void downloadData(int idTable);
    }

    interface Repository {
        void loadStandings(int idTable, final PresenterStandings.OnFinishedListener onFinishedListener);
    }


    interface OnFinishedListener {
        void onFinished(StandingsList noticeArrayList);
        void onFailure(Throwable t);
    }
}
