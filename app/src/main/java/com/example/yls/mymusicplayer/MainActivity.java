package com.example.yls.mymusicplayer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.yls.mymusicplayer.adapter.SongCursorAdapter;
import com.example.yls.mymusicplayer.listener.InitListListener;
import com.example.yls.mymusicplayer.listener.SongListItemClickListener;
import com.example.yls.mymusicplayer.scan.ScanService;

public class MainActivity extends AppCompatActivity implements InitListListener,ServiceConnection{


    ListView songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songList = (ListView) findViewById(R.id.song_list);

        startScanService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.search_music_file){ //扫描
            startScanService();
        }

        return super.onOptionsItemSelected(item);
    }

    private void startScanService(){
        Intent scanIntent = new Intent(this,ScanService.class);
        scanIntent.setAction("com.example.yls.mymusicplayer.scan_music_file");
        bindService(scanIntent,this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void initSongList(Cursor cursor) {
        Log.i(ScanService.TAG, "activity:"+cursor);
        songList.setAdapter(new SongCursorAdapter(this, R.layout.song_item, cursor));
        songList.setOnItemClickListener(new SongListItemClickListener(this));
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        ScanService.ScanServiceBinder binder = (ScanService.ScanServiceBinder) service;
        binder.setActivity(this);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
