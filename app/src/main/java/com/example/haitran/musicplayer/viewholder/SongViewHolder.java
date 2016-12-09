package com.example.haitran.musicplayer.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haitran.musicplayer.R;

/**
 * Created by Hai Tran on 10/19/2016.
 */

public class SongViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageSong;
    private TextView txtName;
    private TextView txtArtist;
    public View line;
    private TextView txtCustom;

    public SongViewHolder(View itemView) {
        super(itemView);
        imageSong = (ImageView) itemView.findViewById(R.id.img_song);
        txtName = (TextView) itemView.findViewById(R.id.txt_name);
        txtArtist = (TextView) itemView.findViewById(R.id.txt_artist);
        line = itemView.findViewById(R.id.view);
        txtCustom=(TextView)itemView.findViewById(R.id.txt_custom_music);
    }

    public ImageView getImageSong() {
        return imageSong;
    }

    public TextView getTxtName() {
        return txtName;
    }

    public TextView getTxtArtist() {
        return txtArtist;
    }

    public TextView getTxtCustom() {
        return txtCustom;
    }
}
