package com.example.haitran.musicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haitran.musicplayer.R;
import com.example.haitran.musicplayer.adapter.AlbumAdapter;
import com.example.haitran.musicplayer.manager.AlbumManager;

/**
 * Created by Hai Tran on 10/19/2016.
 */

public class AlbumFragment extends Fragment{
    private RecyclerView recyclerViewAlbum;
    private AlbumManager albumManager;
    private AlbumAdapter albumAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView(view);
    }

    private void initializeView(View view) {
        recyclerViewAlbum=(RecyclerView)view.findViewById(R.id.recycler_view_album);
        recyclerViewAlbum.setLayoutManager(new GridLayoutManager(getContext(),2));
        albumManager =new AlbumManager(getContext());
        albumAdapter=new AlbumAdapter(albumManager.arrAlbums());
        recyclerViewAlbum.setAdapter(albumAdapter);

    }
}
