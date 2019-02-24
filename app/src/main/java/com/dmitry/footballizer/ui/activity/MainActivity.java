package com.dmitry.footballizer.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dmitry.footballizer.R;
import com.dmitry.footballizer.ui.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment frag = new MainFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.mainContainer, frag);
        fTrans.commit();
    }

    @Override
    public void onBackPressed() {
        if ( getFragmentManager().getBackStackEntryCount() > 0)
        {
            getFragmentManager().popBackStack();
        }
        else{
            super.onBackPressed();
        }
    }
}
