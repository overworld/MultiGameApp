package mds.multigame.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mds.multigame.R;
import mds.multigame.adapter.ScoreAdapter;
import mds.multigame.manager.PlayerManager;

public class SettingsFragment extends Fragment {
    public static final String TAG = "SettingsFragment";

    RecyclerView recyclerView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        recyclerView = view.findViewById(R.id.liste_score);
        ArrayList<String> listjeu = new ArrayList<String>();
        listjeu.add("DragnDrop");
        listjeu.add("Swipe");
        listjeu.add("Fast Tap");
        listjeu.add("Ipac game");
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new ScoreAdapter(this,listjeu));
        return view;

    }
}
