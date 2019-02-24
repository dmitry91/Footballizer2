package com.dmitry.footballizer.ui.presenters;

import com.dmitry.footballizer.models.StandingsList;
import com.dmitry.footballizer.repositorys.StandingRepository;
import com.dmitry.footballizer.ui.interfaces.PresenterStandings;

public class StandingsPresenter implements PresenterStandings.Presenter, PresenterStandings.OnFinishedListener {

    private PresenterStandings.View mView;
    private PresenterStandings.Repository mRepository;

    public StandingsPresenter(PresenterStandings.View mView) {
        this.mView = mView;
        this.mRepository = new StandingRepository();
    }

    @Override
    public void downloadData(int idTable) {
        mRepository.loadStandings(idTable, this);
    }

    @Override
    public void onFinished(StandingsList noticeArrayList) {
        mView.showCompetitions(noticeArrayList);
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }
}
