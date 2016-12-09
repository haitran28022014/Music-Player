package com.example.haitran.musicplayer.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.haitran.musicplayer.R;
import com.example.haitran.musicplayer.fragment.SongFragment;
import com.example.haitran.musicplayer.model.Song;
import com.example.haitran.musicplayer.viewholder.HeaderViewHolder;
import com.example.haitran.musicplayer.viewholder.SongViewHolder;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hai Tran on 10/19/2016.
 */

public class SongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SongFragment.OnClickItemRecyclerView onClickSongPlay;
    private static final int SONG_VIEW = 0;
    private static final int HEADER_VIEW = 1;
    private ArrayList<Object> items;

    public SongAdapter(ArrayList<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case SONG_VIEW:
                View view = inflater.inflate(R.layout.item_song, parent, false);
                viewHolder = new SongViewHolder(view);
                break;
            case HEADER_VIEW:
                View view1 = inflater.inflate(R.layout.item_header, parent, false);
                viewHolder = new HeaderViewHolder(view1);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case SONG_VIEW:
                final Song song = (Song) items.get(position);
                SongViewHolder songViewHolder = (SongViewHolder) holder;
                Glide.with(holder.itemView.getContext())
                        .load(song.getImageSong())
                        .into(songViewHolder.getImageSong());
                Random random = new Random();
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                songViewHolder.getTxtCustom().setBackgroundColor(color);
                songViewHolder.getTxtCustom().setText(song.getNameSong().substring(0,2));
                songViewHolder.getTxtName().setText(song.getNameSong());
                songViewHolder.getTxtArtist().setText(song.getArtistSong());
                if ((position + 1 < items.size() && !(items.get(position + 1) instanceof Song)) || position == items.size() - 1) {
                    songViewHolder.line.setVisibility(View.INVISIBLE);
                } else {
                    songViewHolder.line.setVisibility(View.VISIBLE);
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickSongPlay.onClickItem(song);
                    }
                });
                break;
            case HEADER_VIEW:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.getTxtGroup().setText(items.get(position).toString());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Song) {
            return SONG_VIEW;
        } else {
            return HEADER_VIEW;
        }
    }

    public void setOnClickSongPlay(SongFragment.OnClickItemRecyclerView onClickSongPlay) {
        this.onClickSongPlay = onClickSongPlay;
    }
}
