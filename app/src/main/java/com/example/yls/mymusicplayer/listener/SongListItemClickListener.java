package com.example.yls.mymusicplayer.listener;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.yls.mymusicplayer.Utils;

/**
 * Created by ubuntu on 3/14/16.
 */
public class SongListItemClickListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(Utils.TAG, "SongListItemClickListener onItemClick position:"+position);
    }
}
