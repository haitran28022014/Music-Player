package com.example.haitran.musicplayer.manager;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.haitran.musicplayer.model.Album;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 11/10/2016.
 */

public class AlbumManager {
    private Context context;

    public AlbumManager(Context context) {
        this.context = context;
    }
    public ArrayList<Album> arrAlbums() {
        Cursor cursorAlbum = context.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null
                , MediaStore.Audio.Albums.ALBUM + " ASC");
        ArrayList<Album> arrAlbum = new ArrayList<>();
        if (cursorAlbum != null) {
            int indexIdAlbum = cursorAlbum.getColumnIndex(MediaStore.Audio.Albums._ID);
            int indexTitle = cursorAlbum.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
            int indexArtist = cursorAlbum.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
            int indexImage = cursorAlbum.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
            while (cursorAlbum.moveToNext()) {
                int id = cursorAlbum.getInt(indexIdAlbum);
                String name = cursorAlbum.getString(indexTitle);
                String artist = cursorAlbum.getString(indexArtist);
                String image = cursorAlbum.getString(indexImage);
                arrAlbum.add(new Album(id, name, artist, image));
            }
        }
        cursorAlbum.close();
        return arrAlbum;
    }
}
