package com.dmitry.footballizer.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dmitry.footballizer.R;
import com.dmitry.footballizer.models.Competition;
import com.dmitry.footballizer.ui.interfaces.PresenterCompetitionDetail;
import com.dmitry.footballizer.ui.presenters.CompetitionDetailPresenter;


public class CompetitionFragment extends Fragment implements PresenterCompetitionDetail.View, View.OnClickListener {
    public final static String TAG = "competition_fragment";

    private View mView;
    private CompetitionDetailPresenter competitionDetailPresenter;
    private int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_comp_detail, container, false);
        competitionDetailPresenter = new CompetitionDetailPresenter(this);
        Button btnShowTable = mView.findViewById(R.id.buttonShowTable);
        btnShowTable.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle args = getArguments();
        id = args.getInt("id", 0);
        competitionDetailPresenter.downloadCompetition(id);
    }

    @Override
    public void showCompetition(Competition competition) {
        TextView name = mView.findViewById(R.id.comp_name);
        TextView place = mView.findViewById(R.id.comp_place);
        TextView start = mView.findViewById(R.id.start_date);
        TextView end = mView.findViewById(R.id.end_date);
        name.setText(competition.getName());
        place.setText(competition.getArea().getName());
        start.setText(competition.getCurrentSeason().getStartDate());
        end.setText(competition.getCurrentSeason().getEndDate());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonShowTable:{
                Bundle args = new Bundle();
                args.putInt("id", id);

                FragmentTransaction fTrans = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment = new StandingsFragment();
                fragment.setArguments(args);
                fTrans.replace(R.id.mainContainer, fragment);
                fTrans.addToBackStack(StandingsFragment.TAG);
                fTrans.commit();
            }
        }
    }
}
