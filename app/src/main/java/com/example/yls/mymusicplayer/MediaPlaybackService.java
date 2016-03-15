package com.example.yls.mymusicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MediaPlaybackService extends Service {

    private String playSourcePath;
    MediaPlayer mediaPlayer;

    public MediaPlaybackService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MediaPlaybackServiceBinder();
    }

    public class MediaPlaybackServiceBinder extends Binder{
        public void setPlaySourcePath(String playSourcePath){
            MediaPlaybackService.this.playSourcePath = playSourcePath;
        }

        public void beginPlay(){
            MediaPlaybackService.beginPlay();
        }
    }

    public static void beginPlay(){

    }
}
