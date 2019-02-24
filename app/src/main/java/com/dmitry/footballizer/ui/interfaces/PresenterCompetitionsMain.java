package com.dmitry.footballizer.ui.interfaces;

import android.content.Context;

import com.dmitry.footballizer.models.Competition;

import java.util.ArrayList;

public interface PresenterCompetitionsMain {

    interface View {
        void showCompetitions(ArrayList<Competition> noticeArrayList);
    }

    interface Presenter {
        void downloadData();
    }

    interface Repository {
        void loadCompetitions(final OnFinishedListener onFinishedListener);
    }


    interface OnFinishedListener {
        void onFinished(ArrayList<Competition> noticeArrayList);
        void onFailure(Throwable t);
    }
}
