package com.indibase.conconi.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.indibase.conconi.R;

import org.w3c.dom.Text;

// TODO Add homebutton to FinishedTestActivity
public class FinishedTestActivity extends Activity {

    private TextView lbl_fin_level;
    private TextView lbl_fin_heartbeat;
    private TextView lbl_fin_time;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_test);

        drawTestData();

    }

    public void deleteTest(View view) {

        /* code for removing the finished test */

        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void drawTestData() {
        lbl_fin_heartbeat = (TextView) findViewById(R.id.lbl_fin_heartbeat);
        lbl_fin_level = (TextView) findViewById(R.id.lbl_fin_level);
        lbl_fin_time = (TextView) findViewById(R.id.lbl_fin_time);

        lbl_fin_heartbeat.setText("78");
        lbl_fin_level.setText("14");
        lbl_fin_time.setText("10:01:23");
    }


}
