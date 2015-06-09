package com.indibase.conconi.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.indibase.conconi.R;

public class PresentationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data_presentation);
    }

    public void deleteTest(View view) {

        /* code for removing the finished test */

        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void navigateHome(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
    }

}
