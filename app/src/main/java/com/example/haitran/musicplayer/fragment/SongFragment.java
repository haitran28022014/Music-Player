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
import com.example.haitran.musicplayer.adapter.SongAdapter;
import com.example.haitran.musicplayer.manager.SongManager;
import com.example.haitran.musicplayer.model.Song;

/**
 * Created by Hai Tran on 10/19/2016.
 */

public class SongFragment extends Fragment {
    private RecyclerView recyclerViewSong;
    private SongAdapter songAdapter;
    private SongManager songManager;
    private OnClickItemRecyclerView onClickItemRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onClickItemRecyclerView = (OnClickItemRecyclerView) getActivity();
        return inflater.inflate(R.layout.fragment_song, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView(view);
    }


    private void initializeView(View view) {
        recyclerViewSong = (RecyclerView) view.findViewById(R.id.recycler_view_song);
        recyclerViewSong.setLayoutManager(new LinearLayoutManager(getContext()));
        songManager = new SongManager(getContext());
        songAdapter = new SongAdapter(songManager.arrItemsSong());
        songAdapter.setOnClickSongPlay(onClickItemRecyclerView);
        recyclerViewSong.setAdapter(songAdapter);
    }


    public interface OnClickItemRecyclerView {
        void onClickItem(Song song);
    }

}
