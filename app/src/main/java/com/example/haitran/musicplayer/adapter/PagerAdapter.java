package com.example.haitran.musicplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.haitran.musicplayer.fragment.AlbumFragment;
import com.example.haitran.musicplayer.fragment.ArtistFragment;
import com.example.haitran.musicplayer.fragment.SongFragment;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 10/19/2016.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    public static final String[] COLUMN_NAME = {"Songs", "Albums", "Artists"};
    private ArrayList<Fragment> arrFragment;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        arrFragment = new ArrayList<>();
        arrFragment.add(new SongFragment());
        arrFragment.add(new AlbumFragment());
        arrFragment.add(new ArtistFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return arrFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return COLUMN_NAME[position];
    }
}
