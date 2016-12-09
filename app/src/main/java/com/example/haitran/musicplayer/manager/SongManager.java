package com.example.haitran.musicplayer.manager;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.haitran.musicplayer.model.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Hai Tran on 10/19/2016.
 */

public class SongManager {
    private Context context;

    public SongManager(Context context) {
        this.context = context;
    }

    public ArrayList<Song> arrSong() {
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.TITLE + " ASC");
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        ArrayList<Song> listSong = new ArrayList<>();
        int indexName = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int indexArtist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int indexPath = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        int indexIdAlbum = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
        cursor.moveToFirst();
        while (!cursor.isLast()) {
            String path = cursor.getString(indexPath);
            String name = cursor.getString(indexName);
            String artist = cursor.getString(indexArtist);
            int idAlbums = cursor.getInt(indexIdAlbum);
            listSong.add(new Song(idAlbums, null, name, artist, path));
            cursor.moveToNext();
        }
        cursor.close();

        Cursor cursor1 = context.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART}, null, null, null);
        if (cursor1 != null) {
            while (cursor1.moveToNext()) {
                for (Song song : listSong) {
                    if (cursor1.getInt(0) == song.getIdAlbum()) {
                        song.setImageSong(cursor1.getString(1));
                    }
                }
            }
        }
        cursor1.close();
        Comparator<Song> comparator = new Comparator<Song>() {
            @Override
            public int compare(Song song, Song t1) {
                return song.getNameSong().compareToIgnoreCase(t1.getNameSong());
            }
        };
        Collections.sort(listSong, comparator);
        return listSong;
    }

    public ArrayList<Object> arrItemsSong() {
        ArrayList<Song> arrSong = arrSong();
        ArrayList<Object> songs = new ArrayList<>();
        for (int i = 0; i < arrSong.size(); i++) {
            char firstName = arrSong.get(i).getNameSong().charAt(0);
            if (!Character.isLetter(firstName)) {
                if (i == 0) {
                    songs.add("#");
                    songs.add(arrSong.get(i));
                } else {
                    songs.add(arrSong.get(i));
                }
            } else if (i==0||!String.valueOf(firstName).equalsIgnoreCase(String.valueOf(arrSong.get(i - 1).getNameSong().charAt(0)))) {
                songs.add(arrSong.get(i).getNameSong().toUpperCase().charAt(0));
                songs.add(arrSong.get(i));
            } else {
                songs.add(arrSong.get(i));
            }
        }
        return songs;
    }
}
