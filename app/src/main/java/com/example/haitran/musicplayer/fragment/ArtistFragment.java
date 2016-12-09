package com.example.haitran.musicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haitran.musicplayer.R;
import com.example.haitran.musicplayer.adapter.ArtistAdapter;
import com.example.haitran.musicplayer.manager.ArtistManager;

/**
 * Created by Hai Tran on 10/19/2016.
 */

public class ArtistFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArtistManager artistManager;
    private ArtistAdapter artistAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artist,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView(view);
    }

    private void initializeView(View view) {
        recyclerView=(RecyclerView) view.findViewById(R.id.recycler_view_artist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        artistManager=new ArtistManager(getContext());
        artistAdapter=new ArtistAdapter(artistManager.arrItemArtist());
        recyclerView.setAdapter(artistAdapter);
    }
}
