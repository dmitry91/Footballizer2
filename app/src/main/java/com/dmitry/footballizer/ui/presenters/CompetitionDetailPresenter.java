package com.dmitry.footballizer.ui.presenters;

import android.support.v4.app.Fragment;

import com.dmitry.footballizer.models.Competition;
import com.dmitry.footballizer.repositorys.CompetitionDetailsRepository;
import com.dmitry.footballizer.ui.interfaces.PresenterCompetitionDetail;

public class CompetitionDetailPresenter implements PresenterCompetitionDetail.Presenter, PresenterCompetitionDetail.OnFinishedListener {

    private PresenterCompetitionDetail.View mView;
    private PresenterCompetitionDetail.Repository mRepository;

    public CompetitionDetailPresenter(PresenterCompetitionDetail.View mView) {
        this.mView = mView;
        mRepository = new CompetitionDetailsRepository((Fragment)mView);
    }

    @Override
    public void downloadCompetition(int id) {
        mRepository.loadCompetition(this , id);
    }

    @Override
    public void onFinished(Competition competition) {
        mView.showCompetition(competition);
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }
}
