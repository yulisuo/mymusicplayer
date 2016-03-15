package com.example.yls.mymusicplayer.listener;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.yls.mymusicplayer.MainActivity;
import com.example.yls.mymusicplayer.MediaPlaybackService;
import com.example.yls.mymusicplayer.Utils;
import com.example.yls.mymusicplayer.scan.MediaPlaybackServiceConnection;

import java.security.MessageDigest;

/**
 * Created by ubuntu on 3/14/16.
 */
public class SongListItemClickListener implements AdapterView.OnItemClickListener {

    Context context;
    Intent playService;
    public SongListItemClickListener(Context activity) {
        context = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(Utils.TAG, "SongListItemClickListener onItemClick position:"+position+",data:"+(String)view.getTag());
        playService = new Intent(context, MediaPlaybackService.class);
        context.bindService(playService,new MediaPlaybackServiceConnection((String)view.getTag()), Service.BIND_AUTO_CREATE);
    }
}
