package com.example.haitran.musicplayer.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haitran.musicplayer.R;

/**
 * Created by Hai Tran on 10/19/2016.
 */

public class AlbumViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgAlbum;
    private TextView txtName;
    private TextView txtArtist;

    public AlbumViewHolder(View itemView) {
        super(itemView);
        imgAlbum=(ImageView)itemView.findViewById(R.id.img_album);
        txtName=(TextView)itemView.findViewById(R.id.txt_name_album);
        txtArtist=(TextView)itemView.findViewById(R.id.txt_artist_album);
    }

    public ImageView getImgAlbum() {
        return imgAlbum;
    }

    public TextView getTxtName() {
        return txtName;
    }

    public TextView getTxtArtist() {
        return txtArtist;
    }
}
