package com.indibase.conconi.app;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.indibase.conconi.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void advicesView(View view){
        Intent intent = new Intent(this, AdvicesActivity.class);
        startActivity(intent);
    }
    public void historyView(View view){
       /* Intent intent = new Intent(this, History.class);
        startActivity(intent);*/
    }
    public void beginTest(View view){
      /*  Intent intent = new Intent(this, BeginTest.class);
        startActivity(intent);*/
    }

}
