package com.indibase.conconi.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidplot.Plot;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.indibase.conconi.R;
import com.indibase.conconi.models.DbTest;
import com.indibase.conconi.models.Deflection;
import com.indibase.conconi.models.Measurement;
import com.indibase.conconi.models.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PresentationActivity extends Activity {

    private Test test;

    private XYPlot plot;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data_presentation);

        View view = findViewById(R.id.simpleXYPlot);
        Intent intent = getIntent();
        id = Integer.valueOf(intent.getStringExtra("ITEM_ID"));
        test = DbTest.getTest(this, id, true);
        Log.w("id", String.valueOf(id));
        drawGraphPlot(view);
        updateDataLabels();

    }

    private void updateDataLabels() {

        TextView lblTime = (TextView) findViewById(R.id.lbl_pres_time);
        TextView lblDp = (TextView) findViewById(R.id.lbl_pres_dp);
        TextView lblLvl = (TextView) findViewById(R.id.lbl_pres_lvl);

        int seconds = test.getMeasurements().size();

        Date date = new Date(0,0,0,0,seconds);
        Log.i("", "fdfs");

        lblDp.setText(String.valueOf(test.getDeflection_point()));
        lblTime.setText(test.getTime());
        lblLvl.setText(String.valueOf(test.getLevel()));


        //lblTime.setText();


    }

    private void drawGraphPlot(View view ) {
        initGraphPlot();
        insertValues(view);
        styleGraphPlot();
    }

    private void initGraphPlot() {
        plot = (XYPlot) findViewById(R.id.simpleXYPlot);
    }

    private void insertValues(View view) {
        List s1 = getSeries();
        XYSeries series1 = new SimpleXYSeries(s1,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series 1");

        LineAndPointFormatter s1Format = new LineAndPointFormatter();
        s1Format.setPointLabelFormatter(new PointLabelFormatter());
        s1Format.configure(view.getContext(), R.xml.lpf1);


        List s2 = getLinearPlot(s1, s1.size());
        XYSeries series2 = new SimpleXYSeries(s2,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series 2");

        LineAndPointFormatter s2Format = new LineAndPointFormatter();
        s2Format.setPointLabelFormatter(new PointLabelFormatter());
        s2Format.configure(view.getContext(), R.xml.lpf2);

        plot.addSeries(series2, s2Format);
        plot.addSeries(series1, s1Format);
    }

    private void styleGraphPlot() {
        // Place and size styling for Plot graph
        plot.setPlotMargins(0, 0, 0, 0);
        plot.setPlotPadding(40, 30, 40, 20);
        plot.getGraphWidget().setGridPadding(0, 0, 0, 0);
        plot.getGraphWidget().setMargins(-40, 0, 0, 0);

        // Set plot graph to size of parent
        float parentWidth = (float) View.MeasureSpec.getSize(plot.getMeasuredWidth());
        float parentHeight = (float) View.MeasureSpec.getSize(plot.getMeasuredHeight());
        plot.getGraphWidget().setSize(new SizeMetrics(parentHeight, SizeLayoutType.FILL, parentWidth, SizeLayoutType.FILL));

        //plot.getGraphWidget().getRangeGridLinePaint().setAlpha(255); // sets the horizontal ruler lines
        plot.getGraphWidget().getRangeGridLinePaint().setColor(getResources().getColor(R.color.blue01));
        plot.getGraphWidget().getRangeGridLinePaint().setStrokeWidth(10);

        // Style Plot element
        plot.getGraphWidget().getBackgroundPaint().setColor(Color.WHITE); //Background of Plot view
        plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.WHITE); // graph background
        //plot.setBackgroundColor(Color.WHITE);

        // Hide irrelevant elements in Plot grid and labels
        plot.setBorderStyle(Plot.BorderStyle.NONE, null, null);
        plot.getGraphWidget().getDomainLabelPaint().setColor(Color.TRANSPARENT);
        plot.getGraphWidget().getRangeLabelPaint().setColor(Color.TRANSPARENT);
        plot.getGraphWidget().getDomainOriginLabelPaint().setColor(Color.TRANSPARENT);
        plot.getGraphWidget().getDomainOriginLinePaint().setColor(Color.TRANSPARENT);
        plot.getGraphWidget().getRangeOriginLinePaint().setColor(Color.TRANSPARENT);
        plot.getLegendWidget().getTextPaint().setAlpha(0);
        // Hide legend icon
        SizeLayoutType plot_layouttype = plot.getLegendWidget().getHeightMetric().getLayoutType();
        plot.getLegendWidget().setIconSizeMetrics(new SizeMetrics(0, plot_layouttype , 0, plot_layouttype));
        // plot.setTicksPerRangeLabel(10); // Did nothing
        plot.getGraphWidget().getRangeLabelPaint().setTextSize(0); //removes vertical ruler labels
        plot.getGraphWidget().getDomainLabelPaint().setTextSize(0); //removes horizontal ruler labels
        //plot.getGraphWidget().getCursorLabelPaint().setTextSize(0); //Nothing happened
        //plot.getGraphWidget().getDomainSubGridLinePaint().setAlpha(0); //Nothing happened
        plot.getGraphWidget().getDomainGridLinePaint().setAlpha(0); // sets the vertical ruler lines

        plot.setTicksPerRangeLabel(1);
        plot.getGraphWidget().setDomainLabelOrientation(-45);
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

    private List getLinearPlot(List testResults, int count) {
        List series = new ArrayList();

        int first = (int) testResults.get(0); // remove
        int last = (int) testResults.get(testResults.size() - 1); // remove
        //int linear_increase = ((last-first)/count); // exchange with the following statement

        double linear_increase = Deflection.getAngle(first, last, count);

        Log.w("linear",String.valueOf(linear_increase));

        for (int i = 0; i < count ; i++) {
            series.add(first+(linear_increase*i));
        }
        System.out.println(series);
        return series;
    }
    private List<Integer> getMeasurementToInt(ArrayList<Measurement> measurements){
        List values = new ArrayList();
        for (Measurement m : measurements){
            values.add(m.getBpm());
        }
        return values;
     }
    private List getSeries() {

        List series = new ArrayList(getMeasurementToInt(test.getMeasurements()));


/*
        series.add(110);
        series.add(112);
        series.add(114);
        series.add(113);
        series.add(113);
        series.add(115);
        series.add(117);
        series.add(120);
        series.add(124);
        series.add(122);
        series.add(119);
        series.add(125);
        series.add(131);
        series.add(133);
        series.add(136);
        series.add(140);
        series.add(145);
        series.add(151);
        series.add(156);
        series.add(160);
        series.add(163);
        series.add(166);
        series.add(170);
        series.add(172);
        series.add(174);
        series.add(176);
        series.add(177);
        series.add(179);
        series.add(181);
        series.add(183);
        series.add(186);*/
        System.out.println(series);
        return series;
    }

}
