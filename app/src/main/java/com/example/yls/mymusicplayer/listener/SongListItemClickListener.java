package com.example.yls.mymusicplayer.listener;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.yls.mymusicplayer.MainActivity;
import com.example.yls.mymusicplayer.Utils;

/**
 * Created by ubuntu on 3/14/16.
 */
public class SongListItemClickListener implements AdapterView.OnItemClickListener {

    Context context;
    public SongListItemClickListener(Context activity) {
        context = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(Utils.TAG, "SongListItemClickListener onItemClick position:"+position+",data:"+(String)view.getTag());

    }
}
