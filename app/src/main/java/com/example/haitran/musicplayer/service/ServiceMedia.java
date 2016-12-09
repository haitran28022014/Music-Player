package com.example.haitran.musicplayer.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;

import com.example.haitran.musicplayer.R;
import com.example.haitran.musicplayer.activity.MainActivity;
import com.example.haitran.musicplayer.manager.SongManager;
import com.example.haitran.musicplayer.manager.MusicPlayer;
import com.example.haitran.musicplayer.model.Song;

import java.util.ArrayList;

import static com.example.haitran.musicplayer.activity.MainActivity.indexPlayAndPause;

/**
 * Created by Hai Tran on 11/1/2016.
 */

public class ServiceMedia extends Service {
    private static final int ID_NO_MEDIA = 30;
    private static final String KEY_NEXT = "next";
    private static final String KEY_PREV = "prev";
    private static final String KEY_PLAY_PAUSE = "playAndPause";
    public static final String PREV_ACTION = "com.example.haitran.musicplayer.prev";
    public static final String PLAY_ACTION = "com.example.haitran.musicplayer.play";
    public static final String NEXT_ACTION = "com.example.haitran.musicplayer.next";
    public static final String STOP_FOREGROUND_ACTION = "com.example.haitran.musicplayer.stopforeground";
    private NotificationManagerCompat notification;
    private NotificationCompat.Builder builder;
    private Notification mNotification;
    private RemoteViews views;
    private RemoteViews smallViews;
    private ArrayList<Song> arrSong;
    private int index;


    @Override
    public void onCreate() {
        super.onCreate();
        notification = NotificationManagerCompat.from(this);
        SongManager songManager = new SongManager(this);
        arrSong = songManager.arrSong();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (PLAY_ACTION.equals(intent.getAction())) {
            handlerPlayAndPause();
        } else if (STOP_FOREGROUND_ACTION.equals(intent.getAction())) {
            stopForeground(false);
            stopService(intent);
            MusicPlayer.getInstance().stop();
            notification.cancel(ID_NO_MEDIA);
        } else if (NEXT_ACTION.equals(intent.getAction())) {
            handlerNext();
        } else if (PREV_ACTION.equals(intent.getAction())) {
            handlerPrev();
        }
        return START_NOT_STICKY;
    }

    public void handlerNext() {
        if (index == arrSong.size() - 1) {
            playSong(arrSong.get(0).getDataSong());
            index = 0;
        } else {
            playSong(arrSong.get(index + 1).getDataSong());
            index = index + 1;
        }
        changeDisPlayNo(arrSong.get(index));
        startForeground(ID_NO_MEDIA, mNotification);
        Intent intent=new Intent();
        intent.setAction(NEXT_ACTION);
        intent.putExtra(KEY_NEXT,arrSong.get(index));
        sendBroadcast(intent);
    }

    public void handlerPrev() {
        if (index == 0) {
            playSong(arrSong.get(arrSong.size() - 1).getDataSong());
            index = arrSong.size() - 1;
        } else {
            playSong(arrSong.get(index - 1).getDataSong());
            index = index - 1;
        }
        changeDisPlayNo(arrSong.get(index));
        startForeground(ID_NO_MEDIA, mNotification);
        Intent intent=new Intent();
        intent.setAction(PREV_ACTION);
        intent.putExtra(KEY_PREV,arrSong.get(index));
        sendBroadcast(intent);
    }

    public void playAudioClickItem(Song song) {
        customNotification(song);
        startForeground(ID_NO_MEDIA, mNotification);
        String path = song.getDataSong();
        for (int i = 0; i < arrSong.size(); i++) {
            if (arrSong.get(i).getDataSong().equals(path)) {
                playSong(song.getDataSong());
                index = i;
            }
        }
    }

    public void handlerPlayAndPause() {
        if (indexPlayAndPause == MainActivity.PAUSE) {
            MusicPlayer.getInstance().pause();
            indexPlayAndPause = MainActivity.PLAY;
            views.setImageViewResource(R.id.btn_image_play, R.drawable.ic_play);
            smallViews.setImageViewResource(R.id.btn_image_play, R.drawable.ic_play);
        } else {
            MusicPlayer.getInstance().resume();
            indexPlayAndPause = MainActivity.PAUSE;
            views.setImageViewResource(R.id.btn_image_play, R.drawable.ic_pause);
            smallViews.setImageViewResource(R.id.btn_image_play, R.drawable.ic_pause);
        }
        startForeground(ID_NO_MEDIA, mNotification);
        Intent intent=new Intent();
        intent.setAction(PLAY_ACTION);
        intent.putExtra(KEY_PLAY_PAUSE,indexPlayAndPause);
        sendBroadcast(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinderMedia();

    }


    public void playSong(String pathSong) {
        if (MusicPlayer.getInstance().getState() == MusicPlayer.PLAYER_IDLE) {
            MusicPlayer.getInstance().setUP(pathSong);
            MusicPlayer.getInstance().play();
        } else {
            MusicPlayer.getInstance().stop();
            MusicPlayer.getInstance().setUP(pathSong);
            MusicPlayer.getInstance().play();
        }
    }

    private void changeDisPlayNo(Song song) {
        views.setTextViewText(R.id.txt_name_no, song.getNameSong());
        views.setTextViewText(R.id.txt_artist_no, song.getArtistSong());
        smallViews.setTextViewText(R.id.txt_name_no, song.getNameSong());
        smallViews.setTextViewText(R.id.txt_artist_no, song.getArtistSong());

        if (song.getImageSong() == null || song.getImageSong().equals("")) {
            views.setImageViewResource(R.id.img_avatar_notification, R.drawable.bg_girl);
            smallViews.setImageViewResource(R.id.img_avatar_notification, R.drawable.bg_girl);
        } else {
            views.setImageViewUri(R.id.img_avatar_notification, Uri.parse(song.getImageSong()));
            smallViews.setImageViewUri(R.id.img_avatar_notification, Uri.parse(song.getImageSong()));
        }
    }

    public void customNotification(Song song) {
        views = new RemoteViews(getPackageName(), R.layout.custom_notification);
        smallViews = new RemoteViews(getPackageName(), R.layout.custom_notification_small);

        Intent intentNotification = new Intent();
        intentNotification.setClass(this, MainActivity.class);
        intentNotification.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentNotification,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentPrev = new Intent();
        intentPrev.setClass(this, ServiceMedia.class);
        intentPrev.setAction(PREV_ACTION);
        PendingIntent pendingPrev = PendingIntent.getService(this, 0, intentPrev, 0);

        Intent intentPlay = new Intent();
        intentPlay.setClass(this, ServiceMedia.class);
        intentPlay.setAction(PLAY_ACTION);
        PendingIntent pendingPlay = PendingIntent.getService(this, 0, intentPlay, 0);

        Intent intentNext = new Intent();
        intentNext.setClass(this, ServiceMedia.class);
        intentNext.setAction(NEXT_ACTION);

        PendingIntent pendingNext = PendingIntent.getService(this, 0, intentNext, 0);

        Intent intentClose = new Intent();
        intentClose.setClass(this, ServiceMedia.class);
        intentClose.setAction(STOP_FOREGROUND_ACTION);
        PendingIntent pendingClose = PendingIntent.getService(this, 0, intentClose, 0);

        changeDisPlayNo(song);
        views.setOnClickPendingIntent(R.id.btn_image_prev, pendingPrev);
        views.setOnClickPendingIntent(R.id.btn_image_play, pendingPlay);
        views.setOnClickPendingIntent(R.id.btn_image_next, pendingNext);
        views.setOnClickPendingIntent(R.id.img_close, pendingClose);

        smallViews.setOnClickPendingIntent(R.id.btn_image_play, pendingPlay);
        smallViews.setOnClickPendingIntent(R.id.btn_image_next, pendingNext);
        smallViews.setOnClickPendingIntent(R.id.img_close, pendingClose);

        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_cd);
        builder.setContent(smallViews);
        builder.setCustomBigContentView(views);
        builder.setContentIntent(pendingIntent);
        mNotification = builder.build();
        startForeground(ID_NO_MEDIA, mNotification);
    }

    public class MyBinderMedia extends Binder {
        public ServiceMedia getServiceMedia() {
            return ServiceMedia.this;
        }
    }
}
