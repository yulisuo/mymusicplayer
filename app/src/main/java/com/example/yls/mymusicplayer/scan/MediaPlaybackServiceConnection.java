package com.example.yls.mymusicplayer.scan;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import com.example.yls.mymusicplayer.MediaPlaybackService;

/**
 * Created by ubuntu on 3/15/16.
 */
public class MediaPlaybackServiceConnection implements ServiceConnection {

    String path;
    MediaPlaybackService.MediaPlaybackServiceBinder binder;

    public MediaPlaybackServiceConnection(String path){
        this.path = path;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (MediaPlaybackService.MediaPlaybackServiceBinder)service;
        if(binder != null && path != null){
            binder.setPlaySourcePath(path);

        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
