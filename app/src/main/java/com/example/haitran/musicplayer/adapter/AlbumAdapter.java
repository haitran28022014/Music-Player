package com.example.haitran.musicplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.haitran.musicplayer.R;
import com.example.haitran.musicplayer.model.Album;
import com.example.haitran.musicplayer.viewholder.AlbumViewHolder;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 10/19/2016.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private ArrayList<Album> arrAlbums;

    public AlbumAdapter(ArrayList<Album> arrAlbums){
        this.arrAlbums =arrAlbums;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album,parent,false);
        AlbumViewHolder albumViewHolder=new AlbumViewHolder(view);
        return albumViewHolder;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        Album album= arrAlbums.get(position);
        holder.getTxtName().setText(album.getNameAlbum());
        holder.getTxtArtist().setText(album.getNameArtistAlbum());
        Glide.with(holder.itemView.getContext())
                .load(album.getImageAlbum())
                .error(R.drawable.bg_girl)
                .into(holder.getImgAlbum());
    }

    @Override
    public int getItemCount() {
        return arrAlbums.size();
    }
}
