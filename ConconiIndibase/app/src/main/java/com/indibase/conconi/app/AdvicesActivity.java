package com.indibase.conconi.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.indibase.conconi.R;

public class AdvicesActivity extends Activity {
    TextView lbl_deflectionPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advices);

        lbl_deflectionPoint = (TextView) this.findViewById(R.id.lbl_lastDeflection);;
    }
    public void changeDeflectionPoint(View view){
        lbl_deflectionPoint.setText("170");
    }
}
