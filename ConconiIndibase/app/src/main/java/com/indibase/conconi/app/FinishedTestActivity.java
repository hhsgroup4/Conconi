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
import com.indibase.conconi.models.DbTest;

import org.w3c.dom.Text;

// TODO Add homebutton to FinishedTestActivity
public class FinishedTestActivity extends Activity {

    private TextView lbl_fin_level;
    private TextView lbl_fin_heartbeat;
    private TextView lbl_fin_time;
    private String time;
    private String level;
    private String deflectionPoint;
    public String test_id;

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
        final Intent intent = getIntent();
        deflectionPoint = intent.getStringExtra("DEFLECTION_POINT");
        level = intent.getStringExtra("LEVEL");
        time = intent.getStringExtra("TIME");
        test_id = intent.getStringExtra("ID");
        drawTestData();

    }
    public void statisticsTest(View view){
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void deleteTest(View view) {

        /* code for removing the finished test */
        DbTest.deleteTest(this,test_id);

        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void drawTestData() {
        lbl_fin_heartbeat = (TextView) findViewById(R.id.lbl_fin_heartbeat);
        lbl_fin_level = (TextView) findViewById(R.id.lbl_fin_level);
        lbl_fin_time = (TextView) findViewById(R.id.lbl_fin_time);

        lbl_fin_heartbeat.setText(deflectionPoint);
        lbl_fin_level.setText(level);
        lbl_fin_time.setText(time);
    }


}
