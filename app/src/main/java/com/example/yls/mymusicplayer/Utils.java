package com.example.yls.mymusicplayer;

import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

/**
 * Created by ubuntu on 3/14/16.
 */
public class Utils {

    public static final String TAG = "YMusicPlayer";

    public static void printCursor(Cursor cursor,String[] projection){
        if(cursor != null && projection != null){
            int count = cursor.getCount();
            if(count > 0){
                while(cursor.moveToNext()){
                    printStringArray(projection);
                    StringBuilder sb = new StringBuilder();
                    for (String item:projection) {
                        sb.append(cursor.getString(cursor.getColumnIndex(item))+"\t");
                    }
                    Log.i(Utils.TAG, sb.toString());
                }
            }
        }
    }

    private static void printStringArray(String[] projection) {
        StringBuilder sb = new StringBuilder();
        for (String item:projection) {
            sb.append(item+"\t");
        }
        Log.i(Utils.TAG,sb.toString());
    }
}
