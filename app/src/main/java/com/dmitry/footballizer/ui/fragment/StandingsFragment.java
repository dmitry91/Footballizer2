package com.dmitry.footballizer.ui.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmitry.footballizer.R;
import com.dmitry.footballizer.models.StandingsList;
import com.dmitry.footballizer.ui.adapters.SimpleRecyclerViewAdapter;
import com.dmitry.footballizer.ui.interfaces.PresenterStandings;
import com.dmitry.footballizer.ui.presenters.StandingsPresenter;

import java.util.ArrayList;

public class StandingsFragment extends Fragment implements PresenterStandings.View {

    private SimpleRecyclerViewAdapter adapter;
    public final static String TAG = "standings_fragment";

    private StandingsPresenter presenter;
    private View mView;
    private StandingsList standingsList;
    private int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        id = args.getInt("id", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_standings, container, false);

        presenter = new StandingsPresenter(this);
        presenter.downloadData(id);
        return mView;
    }


    @Override
    public void showCompetitions(StandingsList noticeArrayList) {
        this.standingsList = noticeArrayList;
        ArrayList<String> result = getAllCommands(noticeArrayList);
        // set up the RecyclerView
        RecyclerView recyclerView = mView.findViewById(R.id.recycler_view_standings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SimpleRecyclerViewAdapter(getActivity(), result);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<String> getAllCommands(StandingsList noticeArrayList){
        ArrayList<String> result = new ArrayList<>();

        for(StandingsList.Standings s: standingsList.getStandings()){
            if(s.getType().contains("TOTAL")) {

                if (s.getGroup() != null) {
                    String group = s.getGroup();
                    result.add(group);
                }
                for (int i = 0; i < s.getTable().size(); i++) {
                    String position = s.getTable().get(i).getPosition();
                    String team = s.getTable().get(i).getTeam().getName();
                    result.add(position + ". " + team);

                }
            }
        }
        return result;
    }
}
