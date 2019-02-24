package com.dmitry.footballizer.ui.presenters;

import android.support.v4.app.Fragment;

import com.dmitry.footballizer.models.Competition;
import com.dmitry.footballizer.repositorys.MainRepository;
import com.dmitry.footballizer.ui.interfaces.PresenterCompetitionsMain;

import java.util.ArrayList;

public class MainPresenter implements PresenterCompetitionsMain.Presenter, PresenterCompetitionsMain.OnFinishedListener {

    private PresenterCompetitionsMain.View mView;
    private PresenterCompetitionsMain.Repository mRepository;

    public MainPresenter(PresenterCompetitionsMain.View mView) {
        this.mView = mView;
        mRepository = new MainRepository( ((Fragment)mView).getActivity() );
    }

    @Override
    public void downloadData() {
        mRepository.loadCompetitions(this);
    }

    @Override
    public void onFinished(ArrayList<Competition> noticeArrayList) {
        mView.showCompetitions(noticeArrayList);
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }
}


