package com.dmitry.footballizer.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmitry.footballizer.R;
import com.dmitry.footballizer.models.Competition;
import com.dmitry.footballizer.ui.presenters.MainPresenter;
import com.dmitry.footballizer.ui.adapters.SimpleRecyclerViewAdapter;
import com.dmitry.footballizer.ui.interfaces.PresenterCompetitionsMain;

import java.util.ArrayList;


public class MainFragment extends Fragment implements PresenterCompetitionsMain.View ,SimpleRecyclerViewAdapter.ItemClickListener {

    private SimpleRecyclerViewAdapter adapter;
    public final static String TAG = "main_fragment";

    private MainPresenter presenter;
    private View mView;
    private ArrayList<Competition> noticeArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_main, container, false);

        presenter = new MainPresenter(this);
        presenter.downloadData();
        return mView;
    }


    @Override
    public void showCompetitions(ArrayList<Competition> noticeArrayList) {
        this.noticeArrayList = noticeArrayList;
        // set up the RecyclerView
        RecyclerView recyclerView = mView.findViewById(R.id.recycler_view_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SimpleRecyclerViewAdapter(getActivity(), getNames(noticeArrayList));
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<String> getNames(ArrayList<Competition> noticeArrayList){
        ArrayList<String> names = new ArrayList<>();
        for (Competition c:noticeArrayList){
            names.add(c.getName());
        }
        return names;
    }

    @Override
    public void onItemClick(View view, int position) {
        Bundle args = new Bundle();
        args.putInt("id", noticeArrayList.get(position).getId());

        FragmentTransaction fTrans = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment = new CompetitionFragment();
        fragment.setArguments(args);
        fTrans.replace(R.id.mainContainer, fragment);
        fTrans.addToBackStack(CompetitionFragment.TAG);
        fTrans.commit();
    }
}
