package com.example.haitran.musicplayer.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.haitran.musicplayer.R;
import com.example.haitran.musicplayer.model.Artist;

import java.util.Random;

/**
 * Created by Hai Tran on 11/10/2016.
 */

public class ArtistViewHolder extends RecyclerView.ViewHolder {
    private TextView txtImageArtist;
    private TextView txtNameArtist;
    private TextView txtNumber;
    private View lineArtist;

    public ArtistViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        txtImageArtist=(TextView)itemView.findViewById(R.id.txt_image_artist);
        txtNameArtist=(TextView)itemView.findViewById(R.id.txt_name_artist);
        txtNumber=(TextView)itemView.findViewById(R.id.txt_number_album_songs);
        lineArtist=itemView.findViewById(R.id.view_artist);
    }

    public void handlerItemView(Artist artist){
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        txtImageArtist.setBackgroundColor(color);
        txtImageArtist.setText(artist.getNameArtist().substring(0,2));
        txtNameArtist.setText(artist.getNameArtist());
        txtNumber.setText(artist.getNumberAlbum()+" album | "+artist.getNumberSong()+" songs");
    }

    public View getLineArtist() {
        return lineArtist;
    }
}
