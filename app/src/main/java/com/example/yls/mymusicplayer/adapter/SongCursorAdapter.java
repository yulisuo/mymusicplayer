package com.example.yls.mymusicplayer.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.yls.mymusicplayer.R;

/**
 * Created by ubuntu on 12/11/15.
 */
public class SongCursorAdapter extends CursorAdapter{

    int layout;

    public SongCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public SongCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    public SongCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    public SongCursorAdapter(Context context, int layout,Cursor c){
        super(context, c);
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = View.inflate(context, layout,null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = (TextView) view.findViewById(R.id.tv_name);
        TextView duration = (TextView) view.findViewById(R.id.tv_duration);
        name.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
        duration.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
    }
}
