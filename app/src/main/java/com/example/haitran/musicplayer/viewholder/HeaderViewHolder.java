package com.example.haitran.musicplayer.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.haitran.musicplayer.R;

/**
 * Created by Hai Tran on 10/19/2016.
 */

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    private TextView txtGroup;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        txtGroup=(TextView)itemView.findViewById(R.id.txt_group);
    }

    public void handlerGroup(String nameGroup){
        txtGroup.setText(nameGroup);
    }

    public TextView getTxtGroup() {
        return txtGroup;
    }
}
