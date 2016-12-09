package com.example.haitran.musicplayer.model;

/**
 * Created by Hai Tran on 11/10/2016.
 */

public class Artist {
    private String nameArtist;
    private int numberAlbum;
    private int numberSong;

    public Artist(String nameArtist, int numberAlbum, int numberSong) {
        this.nameArtist = nameArtist;
        this.numberAlbum = numberAlbum;
        this.numberSong = numberSong;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public int getNumberAlbum() {
        return numberAlbum;
    }

    public int getNumberSong() {
        return numberSong;
    }
}
