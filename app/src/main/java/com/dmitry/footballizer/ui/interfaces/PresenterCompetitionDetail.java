package com.dmitry.footballizer.ui.interfaces;

import com.dmitry.footballizer.models.Competition;

public interface PresenterCompetitionDetail {
    interface View {
        void showCompetition(Competition competition);
    }

    interface Presenter {
        void downloadCompetition(int id);
    }

    interface Repository {
        void loadCompetition(final PresenterCompetitionDetail.OnFinishedListener onFinishedListener, int id);
    }

    interface OnFinishedListener {
        void onFinished(Competition competition);
        void onFailure(Throwable t);
    }

}
