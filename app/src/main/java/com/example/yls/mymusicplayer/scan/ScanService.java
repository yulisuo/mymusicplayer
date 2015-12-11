package com.example.yls.mymusicplayer.scan;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.yls.mymusicplayer.MainActivity;

import java.io.File;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ScanService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SCAN_MUSIC_FILE = "com.example.yls.mymusicplayer.scan_music_file";
    private static final String ACTION_BAZ = "com.example.yls.mymusicplayer.scan.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.yls.mymusicplayer.scan.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.yls.mymusicplayer.scan.extra.PARAM2";
    public static final String TAG = "ScanService";

    private static final Uri INTERNAL_URI = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
    private Intent intent;
    private MainActivity activity;

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ScanService.class);
        intent.setAction(ACTION_SCAN_MUSIC_FILE);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ScanService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public ScanService() {
        super("ScanService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        this.intent = intent;
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SCAN_MUSIC_FILE.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionFoo(param1, param2);
                //scanMusicFile();
                scanMediaInternalStorage();
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void scanMediaInternalStorage(){
        String [] projection = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE
        };

        Cursor cursor = this.getContentResolver().query(INTERNAL_URI, projection,null,null,"");
        Log.i(TAG,"scan cursor:"+cursor);
        activity.initSongList(cursor);

    }

    private void scanMusicFile(){
        Log.i(TAG, "scanMusicFile begin...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Environment.MEDIA_MOUNTED
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            File sdFile = Environment.getExternalStorageDirectory();
            scanMusicReserse(sdFile);
        }
    }

    private void scanMusicReserse(File f){
        if(f.isDirectory()){
            File[] fs = f.listFiles();
            if(fs != null){
                for (File f1:fs){
                    scanMusicReserse(f1);
                }
            }
        }else{
            String path = f.getAbsolutePath();
            if(path.endsWith(".mp3")){
                addMusicFile(f);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
//        scanMediaInternalStorage();
        return new ScanServiceBinder();
    }



    public class ScanServiceBinder extends Binder{
        public void setActivity(MainActivity ma){
            activity = ma;
            scanMediaInternalStorage();
        }
    }

    private void addMusicFile(File f){
        Log.i(TAG,"add music file:"+f.getAbsolutePath());
    }
}

