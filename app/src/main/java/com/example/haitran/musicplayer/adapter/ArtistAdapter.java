package com.example.haitran.musicplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haitran.musicplayer.R;
import com.example.haitran.musicplayer.model.Artist;
import com.example.haitran.musicplayer.viewholder.ArtistViewHolder;
import com.example.haitran.musicplayer.viewholder.HeaderViewHolder;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 11/10/2016.
 */

public class ArtistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_ARTIST = 0;
    public static final int VIEW_HEADER = 1;
    private ArrayList<Object> items;

    public ArtistAdapter(ArrayList<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEW_ARTIST:
                View view = inflater.inflate(R.layout.item_artist, parent, false);
                viewHolder = new ArtistViewHolder(view);
                break;
            case VIEW_HEADER:
                View view1 = inflater.inflate(R.layout.item_header, parent, false);
                viewHolder = new HeaderViewHolder(view1);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_ARTIST:
                Artist artist = (Artist) items.get(position);
                ArtistViewHolder artistViewHolder = (ArtistViewHolder) holder;
                artistViewHolder.handlerItemView(artist);
                if ((position + 1 < items.size() && !(items.get(position + 1) instanceof Artist)) || position == items.size() - 1) {
                    artistViewHolder.getLineArtist().setVisibility(View.INVISIBLE);
                } else {
                    artistViewHolder.getLineArtist().setVisibility(View.VISIBLE);
                }
                break;
            case VIEW_HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.handlerGroup(items.get(position).toString());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Artist) {
            return VIEW_ARTIST;
        } else {
            return VIEW_HEADER;
        }
    }
}
