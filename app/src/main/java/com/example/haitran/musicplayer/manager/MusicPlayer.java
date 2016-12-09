package com.example.haitran.musicplayer.manager;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Hai Tran on 10/29/2016.
 */

public class MusicPlayer {
    public static final int PLAYER_IDLE = -1;
    public static final int PLAYER_PLAY = 1;
    public static final int PLAYER_PAUSE = 2;
    public static final int PLAYER_STOP = 3;
    private int state;

    public static MusicPlayer instance;


    public static MusicPlayer getInstance() {
        if (instance==null){
            instance=new MusicPlayer();
        }
        return instance;
    }


    private MediaPlayer mediaPlayer;

    private MusicPlayer() {
        state = PLAYER_IDLE;
    }

    public void setUP(String path) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (state == PLAYER_IDLE) {
            mediaPlayer.start();
            state = PLAYER_PLAY;
        }
    }

    public void pause() {
        if (state == PLAYER_PLAY) {
            mediaPlayer.pause();
            state = PLAYER_PAUSE;
        }
    }

    public void resume() {
        if (state == PLAYER_PAUSE) {
            mediaPlayer.start();
            state = PLAYER_PLAY;
        }
    }

    public void stop() {
        if (state == PLAYER_PLAY || state == PLAYER_PAUSE) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            state = PLAYER_IDLE;
        }
    }


    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public int getState() {
        return state;
    }
}
