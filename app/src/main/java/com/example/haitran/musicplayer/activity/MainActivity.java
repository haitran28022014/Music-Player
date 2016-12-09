package com.example.haitran.musicplayer.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haitran.musicplayer.R;
import com.example.haitran.musicplayer.adapter.PagerAdapter;
import com.example.haitran.musicplayer.fragment.SongFragment;
import com.example.haitran.musicplayer.manager.MusicPlayer;
import com.example.haitran.musicplayer.model.Song;
import com.example.haitran.musicplayer.service.ServiceMedia;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener
        , SongFragment.OnClickItemRecyclerView
        , SearchView.OnQueryTextListener
        , SeekBar.OnSeekBarChangeListener {
    private static final String KEY_NEXT = "next";
    private static final String KEY_PREV = "prev";
    private static final String KEY_PLAY_PAUSE = "playAndPause";
    public static final String PREV_ACTION = "com.example.haitran.musicplayer.prev";
    public static final String PLAY_ACTION = "com.example.haitran.musicplayer.play";
    public static final String NEXT_ACTION = "com.example.haitran.musicplayer.next";
    public static int indexPlayAndPause = 0;
    public static final int PLAY = 0;
    public static final int PAUSE = 1;
    private BroadCastMainActivity broadCast;
    private SearchView searchView;
    private TabLayout tabLayout;
    private SeekBar seekBar;
    private ViewPager viewPager;
    private TextView txtImagePlay;
    private TextView txtBegin;
    private TextView txtEnd;
    private PagerAdapter pagerAdapter;
    private ImageView imgBackSong;
    private ImageView imgNextSong;
    private ImageView imgPlaySong;
    private ImageView imgRepeat;
    private ImageView imgShuffle;
    private Toolbar toolbar;
    private ImageView imgSongPlay;
    private TextView txtNameSong;
    private TextView txtArtistSong;
    private TextView txtName;
    private TextView txtArtist;
    private ImageView imgPlayPause;
    private LinearLayout bottomSheet;
    private RelativeLayout relativeLayout;
    private BottomSheetBehavior bottomSheetBehavior;
    private CircleImageView circleImageView;
    private ServiceMedia mService;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponent();
        initializeListener();
        handlerService();
        initializeData();
    }

    private void initializeComponent() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        imgPlayPause = (ImageView) findViewById(R.id.img_play_pause);
        imgSongPlay = (ImageView) findViewById(R.id.img_song_play);
        imgBackSong = (ImageView) findViewById(R.id.img_back_song);
        imgNextSong = (ImageView) findViewById(R.id.img_next_song);
        imgPlaySong = (ImageView) findViewById(R.id.img_play_song);
        imgRepeat = (ImageView) findViewById(R.id.img_repeat);
        imgShuffle = (ImageView) findViewById(R.id.img_shuffle);
        txtName = (TextView) findViewById(R.id.txt_name_play);
        txtNameSong = (TextView) findViewById(R.id.txt_name_song);
        txtArtistSong = (TextView) findViewById(R.id.txt_artist_song);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        txtBegin = (TextView) findViewById(R.id.txt_time_begin);
        txtEnd = (TextView) findViewById(R.id.txt_time_end);
        bottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        txtImagePlay = (TextView) findViewById(R.id.txt_custom_image_play);
        txtArtist = (TextView) findViewById(R.id.txt_artist_play);
        circleImageView = (CircleImageView) findViewById(R.id.circle_image);
    }

    public void initializeListener() {
        bottomSheetBehavior.setHideable(false);
        imgPlayPause.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        imgBackSong.setOnClickListener(this);
        imgNextSong.setOnClickListener(this);
        imgPlaySong.setOnClickListener(this);
        imgRepeat.setOnClickListener(this);
        imgShuffle.setOnClickListener(this);
    }

    private void initializeData() {
        broadCast = new BroadCastMainActivity();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NEXT_ACTION);
        intentFilter.addAction(PREV_ACTION);
        intentFilter.addAction(PLAY_ACTION);
        registerReceiver(broadCast, intentFilter);
        relativeLayout.setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("Music");
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    /**
     * B1:Connected Service
     */
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ServiceMedia.MyBinderMedia media = (ServiceMedia.MyBinderMedia) iBinder;
            mService = media.getServiceMedia();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private void handlerService() {
        Intent intent = new Intent();
        intent.setClass(this, ServiceMedia.class);
        startService(intent);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_play_pause:
                mService.handlerPlayAndPause();
                imgPlaySong.setImageLevel(indexPlayAndPause);
                imgPlayPause.setImageLevel(indexPlayAndPause);
                break;
            case R.id.relative_layout:
                break;
            case R.id.img_back_song:
                mService.handlerPrev();
                break;
            case R.id.img_next_song:
                mService.handlerNext();
                break;
            case R.id.img_play_song:
                mService.handlerPlayAndPause();
                imgPlayPause.setImageLevel(indexPlayAndPause);
                imgPlaySong.setImageLevel(indexPlayAndPause);
                break;
            case R.id.img_repeat:
                break;
            case R.id.img_shuffle:
                break;
            default:
                break;
        }
    }


    @Override
    public void onClickItem(Song song) {
        if (mService != null) {
            mService.playAudioClickItem(song);
        }
        relativeLayout.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.custom_circle_image);
        circleImageView.setAnimation(animation);
        updateUISong(song);
        updateTime();
    }

    public void updateUISong(Song song) {
        imgPlayPause.setImageLevel(PAUSE);
        imgPlaySong.setImageLevel(PAUSE);
        indexPlayAndPause = PAUSE;
        Glide.with(this).load(song.getImageSong()).into(imgSongPlay);
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        txtImagePlay.setBackgroundColor(color);
        txtImagePlay.setText(String.valueOf(song.getNameSong().charAt(0)));
        txtName.setText(song.getNameSong());
        txtArtist.setText(song.getArtistSong());
        txtNameSong.setText(song.getNameSong());
        txtArtistSong.setText(song.getArtistSong());
    }

    public void updateTime() {
        handler = new Handler();
        handler.postDelayed(updateTimeTask, 100);
    }

    private Runnable updateTimeTask = new Runnable() {
        @Override
        public void run() {
            if (MusicPlayer.getInstance().getState() == MusicPlayer.PLAYER_IDLE) {
                return;
            }
            long totalDuration = MusicPlayer.getInstance().getMediaPlayer().getDuration();
            long currentDuration = MusicPlayer.getInstance().getMediaPlayer().getCurrentPosition();
            txtBegin.setText(milliSecondsToTimer(currentDuration) + "");
            txtEnd.setText(milliSecondsToTimer(totalDuration) + "");
            int progress = (getProgressPercentage(currentDuration, totalDuration));
            seekBar.setProgress(progress);
            handler.postDelayed(this, 100);
        }
    };

    public int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        return percentage.intValue();
    }

    public int progressToTimer(int progress, int totalDuration) {
        int currentDuration;
        totalDuration = (totalDuration / 1000);
        currentDuration = (int) ((((double) progress) / 100) * totalDuration);

        return currentDuration * 1000;
    }


    public String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString;
        String minutesString;

        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        if (hours > 0) {
            finalTimerString = hours + ":";
        }
        if (minutes < 10) {
            minutesString = "0" + minutes;
        } else {
            minutesString = "" + minutes;
        }
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutesString + ":" + secondsString;

        return finalTimerString;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        handler.removeCallbacks(updateTimeTask);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        handler.removeCallbacks(updateTimeTask);
        int totalDuration = MusicPlayer.getInstance().getMediaPlayer().getDuration();
        int currentDuration = progressToTimer(seekBar.getProgress(), totalDuration);
        MusicPlayer.getInstance().getMediaPlayer().seekTo(currentDuration);
        updateTime();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        unregisterReceiver(broadCast);
        super.onDestroy();
    }

    private class BroadCastMainActivity extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case NEXT_ACTION:
                    Song song = (Song) intent.getSerializableExtra(KEY_NEXT);
                    updateUISong(song);
                    break;
                case PREV_ACTION:
                    Song song1 = (Song) intent.getSerializableExtra(KEY_PREV);
                    updateUISong(song1);
                    break;
                case PLAY_ACTION:
                    int mPlay = intent.getIntExtra(KEY_PLAY_PAUSE, 0);
                    imgPlayPause.setImageLevel(mPlay);
                    imgPlaySong.setImageLevel(mPlay);
                    break;
                default:
                    break;
            }
        }
    }
}
