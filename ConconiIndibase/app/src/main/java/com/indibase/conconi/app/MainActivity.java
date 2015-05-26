package com.indibase.conconi.app;
import android.app.Activity;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.indibase.conconi.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri tests = Uri.parse("content://com.indibase.provider.conconi/test");
        Cursor c;
        CursorLoader cursorLoader = new CursorLoader(
                this,
                tests,
                null,
                null,
                null,
                null);
        c = cursorLoader.loadInBackground();

        Log.d("lifecycle", DatabaseUtils.dumpCursorToString(c));
    }

}
