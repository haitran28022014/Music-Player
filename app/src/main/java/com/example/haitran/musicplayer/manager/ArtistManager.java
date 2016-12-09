package com.example.haitran.musicplayer.manager;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.haitran.musicplayer.model.Artist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Hai Tran on 11/10/2016.
 */

public class ArtistManager {
    private Context context;

    public ArtistManager(Context context) {
        this.context = context;
    }

    public ArrayList<Artist> arrArtist() {
        Cursor cursorArtist = context.getContentResolver().query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null, null
                , MediaStore.Audio.Artists.ARTIST + " ASC");
        ArrayList<Artist> arrArtists = new ArrayList<>();
        while (cursorArtist != null && cursorArtist.moveToNext()) {
            String nameArtist = cursorArtist.getString(cursorArtist.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
            int numberSong = cursorArtist.getInt(cursorArtist.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS));
            int numberAlbums = cursorArtist.getInt(cursorArtist.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS));
            arrArtists.add(new Artist(nameArtist, numberAlbums, numberSong));
        }
        Comparator<Artist> comparator = new Comparator<Artist>() {
            @Override
            public int compare(Artist artist, Artist t1) {
                return artist.getNameArtist().compareToIgnoreCase(t1.getNameArtist());
            }
        };
        Collections.sort(arrArtists, comparator);
        return arrArtists;
    }

    public ArrayList<Object> arrItemArtist() {
        ArrayList<Artist> arrArtists = arrArtist();
        ArrayList<Object> itemArtists = new ArrayList<>();
        for (int i = 0; i < arrArtists.size(); i++) {
            char firstName = arrArtists.get(i).getNameArtist().charAt(0);
            if (!Character.isLetter(firstName)) {
                if (i == 0) {
                    itemArtists.add("#");
                    itemArtists.add(arrArtists.get(i));
                } else {
                    itemArtists.add(arrArtists.get(i));
                }
            } else if (i==0||!String.valueOf(firstName).equals(String.valueOf(arrArtists.get(i - 1).getNameArtist().charAt(0)))) {
                itemArtists.add(arrArtists.get(i).getNameArtist().toUpperCase().charAt(0));
                itemArtists.add(arrArtists.get(i));
            } else {
                itemArtists.add(arrArtists.get(i));
            }
        }
        return itemArtists;
    }
}
