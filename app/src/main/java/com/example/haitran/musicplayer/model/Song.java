package com.example.haitran.musicplayer.model;

import java.io.Serializable;

/**
 * Created by Hai Tran on 10/19/2016.
 */

public class Song implements Serializable{
    private int idAlbum;
    private String imageSong;
    private String nameSong;
    private String artistSong;
    private String dataSong;

    public Song(int idAlbum, String imageSong, String nameSong, String artist,String data) {
        this.idAlbum = idAlbum;
        this.imageSong = imageSong;
        this.nameSong = nameSong;
        this.artistSong = artist;
        this.dataSong =data;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public String getImageSong() {
        return imageSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public String getArtistSong() {
        return artistSong;
    }

    public String getDataSong() {
        return dataSong;
    }

    public void setImageSong(String imageSong) {
        this.imageSong = imageSong;
    }

}
