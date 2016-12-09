package com.example.haitran.musicplayer.model;

/**
 * Created by Hai Tran on 11/10/2016.
 */

public class Album {
    private int idAlbum;
    private String nameAlbum;
    private String nameArtistAlbum;
    private String imageAlbum;

    public Album(int idAlbum, String nameAlbum, String nameArtistAlbum, String imageAlbum) {
        this.idAlbum = idAlbum;
        this.nameAlbum = nameAlbum;
        this.nameArtistAlbum = nameArtistAlbum;
        this.imageAlbum = imageAlbum;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public String getNameArtistAlbum() {
        return nameArtistAlbum;
    }

    public String getImageAlbum() {
        return imageAlbum;
    }
}
