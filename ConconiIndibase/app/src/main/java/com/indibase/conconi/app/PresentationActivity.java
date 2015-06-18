package com.indibase.conconi.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.androidplot.Plot;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;
import com.indibase.conconi.R;
import com.indibase.conconi.models.DbTest;
import com.indibase.conconi.models.Deflection;
import com.indibase.conconi.models.Measurement;
import com.indibase.conconi.models.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PresentationActivity extends Activity {

    private Test test;
    private boolean PORTRAIT_ORIENTATION = true;
    private XYPlot plot;
    private int id;

    // text content
    private TextView lbl_advice_name;
    private TextView lbl_advice_mintime;
    private TextView lbl_advice_maxtime;
    private TextView lbl_advice_minpercent;
    private TextView lbl_advice_maxpercent;
    private TextView lbl_advice_goal;
    private TextView lbl_advice_usage;
    private TextView lbl_advice_frequency;
    private TextView lbl_advice_description;
    private TextView lbl_advice_howto;

    // Styling elements
    private TextView lbl_header_advice_name;
    private TextView lbl_header_advice_mintime;
    private TextView lbl_header_advice_maxtime;
    private TextView lbl_header_advice_minpercent;
    private TextView lbl_header_advice_maxpercent;
    private TextView lbl_header_advice_goal;
    private TextView lbl_header_advice_usage;
    private TextView lbl_header_advice_frequency;
    private TextView lbl_header_advice_description;
    private TextView lbl_header_advice_howto;
    private LinearLayout advice_popup_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_test_data_presentation);

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            this.PORTRAIT_ORIENTATION = true;
            setContentView(R.layout.activity_test_data_presentation);
        } else {
            this.PORTRAIT_ORIENTATION = false;
            setContentView(R.layout.activity_test_data_presentation_landscape);
        }

        View view = findViewById(R.id.simpleXYPlot);
        Intent intent = getIntent();
        id = Integer.valueOf(intent.getStringExtra("ITEM_ID"));
        test = DbTest.getTest(this, id, true);

        Log.w("id", String.valueOf(id));

        if (PORTRAIT_ORIENTATION) {
            updateDataLabels();
        }
        drawGraphPlot(view);

        if (PORTRAIT_ORIENTATION) {
            final ImageView btn_advice_1 = (ImageView) findViewById(R.id.btn_advice_1);
            final ImageView btn_advice_2 = (ImageView) findViewById(R.id.btn_advice_2);
            final ImageView btn_advice_3 = (ImageView) findViewById(R.id.btn_advice_3);
            final ImageView btn_advice_4 = (ImageView) findViewById(R.id.btn_advice_4);
            final ImageView btn_advice_5 = (ImageView) findViewById(R.id.btn_advice_5);

            setupAdviceBtnListeners(view, btn_advice_1);
            setupAdviceBtnListeners(view, btn_advice_2);
            setupAdviceBtnListeners(view, btn_advice_3);
            setupAdviceBtnListeners(view, btn_advice_4);
            setupAdviceBtnListeners(view, btn_advice_5);
        }
    }

    private void setupAdviceBtnListeners(View view, final ImageView imageView) {

        imageView.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);

                View popupView = layoutInflater.inflate(R.layout.popup_advice_detailes, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                launchPopup(popupView, arg0.getId());

                ImageButton btnDismiss = (ImageButton) popupView.findViewById(R.id.dismiss_popup);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAtLocation(imageView, Gravity.TOP | Gravity.CENTER, 0, 100);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void updateDataLabels() {
        TextView lblTime = (TextView) findViewById(R.id.lbl_pres_time);
        TextView lblDp = (TextView) findViewById(R.id.lbl_pres_dp);
        TextView lblLvl = (TextView) findViewById(R.id.lbl_pres_lvl);

        lblDp.setText(String.valueOf(test.getDeflection_point()));
        lblTime.setText(test.getTime());
        lblLvl.setText(String.valueOf(test.getLevel()));
    }

    private void drawGraphPlot(View view) {
        initGraphPlot();
        insertValues(view);
        if (PORTRAIT_ORIENTATION) {
            styleGraphPlot();
        } else {
            styleGraphPlotLandscape();
        }

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
        final XYSeries series2 = new SimpleXYSeries(s2,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series 2");

        LineAndPointFormatter s2Format = new LineAndPointFormatter();
        s2Format.setPointLabelFormatter(new PointLabelFormatter());
        s2Format.configure(view.getContext(), R.xml.lpf2);

        plot.addSeries(series2, s2Format);
        plot.addSeries(series1, s1Format);

        //plot.setDomainStep(XYStepMode.SUBDIVIDE, 5);
        plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 10);
        //plot.setDomainStepValue(33);

        plot.setRangeValueFormat(new DecimalFormat("0"));
        plot.setDomainValueFormat(new DecimalFormat("0"));
    }

    private void styleGraphPlotLandscape() {
        // Place and size styling for Plot graph
        //plot.setPlotMargins(0, 0, 0, 0);
        //plot.setPlotPadding(0, 0, 0, 0);
        //plot.getGraphWidget().setGridPadding(20, 20, 20, 20);
        plot.getGraphWidget().setMargins(30, 40, 40, 40);

        // Set plot graph to size of parent
        float parentWidth = (float) View.MeasureSpec.getSize(plot.getMeasuredWidth());
        float parentHeight = (float) View.MeasureSpec.getSize(plot.getMeasuredHeight());
        //plot.getGraphWidget().setSize(new SizeMetrics(parentHeight, SizeLayoutType.FILL, parentWidth, SizeLayoutType.FILL));

        //plot.getGraphWidget().getRangeGridLinePaint().setAlpha(255); // sets the horizontal ruler lines
        plot.getGraphWidget().getRangeGridLinePaint().setColor(getResources().getColor(R.color.blue01));
        plot.getGraphWidget().getRangeGridLinePaint().setStrokeWidth(10);

        // Style Plot element
        plot.getGraphWidget().getBackgroundPaint().setColor(Color.WHITE); //Background of Plot view
        plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.WHITE); // graph background
        //plot.setBackgroundColor(Color.WHITE);

        // Hide and style irrelevant elements in Plot grid and labels
        plot.setBorderStyle(Plot.BorderStyle.NONE, null, null);
        plot.getGraphWidget().getDomainLabelPaint().setColor(getResources().getColor(R.color.blue01));
        plot.getGraphWidget().getDomainLabelPaint().setFakeBoldText(true);
        plot.getGraphWidget().getRangeLabelPaint().setColor(getResources().getColor(R.color.blue01));
        plot.getGraphWidget().getRangeLabelPaint().setFakeBoldText(true);
        plot.getGraphWidget().getDomainOriginLabelPaint().setColor(Color.TRANSPARENT);
        plot.getGraphWidget().getRangeOriginLabelPaint().setColor(Color.TRANSPARENT);
        plot.getGraphWidget().getDomainOriginLinePaint().setColor(Color.TRANSPARENT);
        plot.getGraphWidget().getRangeOriginLinePaint().setColor(Color.TRANSPARENT);
        plot.getLegendWidget().getTextPaint().setAlpha(0);

        SizeLayoutType plot_layouttype = plot.getLegendWidget().getHeightMetric().getLayoutType();
        plot.getLegendWidget().setIconSizeMetrics(new SizeMetrics(0, plot_layouttype, 0, plot_layouttype));
        // plot.setTicksPerRangeLabel(10); // Did nothing
        plot.getGraphWidget().getRangeLabelPaint().setTextSize(32); //removes vertical ruler labels
        plot.getGraphWidget().getDomainLabelPaint().setTextSize(32); //removes horizontal ruler labels
        //plot.getGraphWidget().getCursorLabelPaint().setTextSize(0); //Nothing happened
        //plot.getGraphWidget().getDomainSubGridLinePaint().setAlpha(0); //Nothing happened
        plot.getGraphWidget().getDomainGridLinePaint().setAlpha(0); // sets the vertical ruler lines

        plot.setTicksPerRangeLabel(1);
        //plot.getGraphWidget().setDomainLabelOrientation(-45);
    }

    // Customization of AndroidPlot API
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
        plot.getLegendWidget().setIconSizeMetrics(new SizeMetrics(0, plot_layouttype, 0, plot_layouttype));
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

        DbTest.deleteTest(this, String.valueOf(id));

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

        Log.w("linear", String.valueOf(linear_increase));

        for (int i = 0; i < count; i++) {
            series.add(first + (linear_increase * i));
        }
        System.out.println(series);
        return series;
    }

    private List<Integer> getMeasurementToInt(ArrayList<Measurement> measurements) {
        List values = new ArrayList();
        for (Measurement m : measurements) {
            values.add(m.getBpm());
        }
        return values;
    }

    private List getSeries() {

        List series = new ArrayList(getMeasurementToInt(test.getMeasurements()));

        System.out.println(series);
        return series;
    }

    private void launchPopup(View popupView, int selection) {
        // text content
        this.lbl_advice_name = (TextView) popupView.findViewById(R.id.lbl_advice_name);
        this.lbl_advice_mintime = (TextView) popupView.findViewById(R.id.lbl_advice_mintime);
        this.lbl_advice_maxtime = (TextView) popupView.findViewById(R.id.lbl_advice_maxtime);
        this.lbl_advice_minpercent = (TextView) popupView.findViewById(R.id.lbl_advice_minpercent);
        this.lbl_advice_maxpercent = (TextView) popupView.findViewById(R.id.lbl_advice_maxpercent);
        this.lbl_advice_goal = (TextView) popupView.findViewById(R.id.lbl_advice_goal);
        this.lbl_advice_usage = (TextView) popupView.findViewById(R.id.lbl_advice_usage);
        this.lbl_advice_frequency = (TextView) popupView.findViewById(R.id.lbl_advice_frequency);
        this.lbl_advice_description = (TextView) popupView.findViewById(R.id.lbl_advice_description);
        this.lbl_advice_howto = (TextView) popupView.findViewById(R.id.lbl_advice_howto);

        switch (selection) {
            case R.id.btn_advice_1:
                // populate with data
                lbl_advice_name.setText(R.string.recover_training_name);
                lbl_advice_mintime.setText(R.string.recover_training_minTime);
                lbl_advice_maxtime.setText(R.string.recover_training_maxTime);
                lbl_advice_minpercent.setText(R.string.recover_training_minPercentBPM);
                lbl_advice_maxpercent.setText(R.string.recover_training_maxPercentBPM);
                lbl_advice_goal.setText(R.string.recover_training_goal);
                lbl_advice_usage.setText(R.string.recover_training_usage);
                lbl_advice_frequency.setText(R.string.recover_training_frequency);
                lbl_advice_description.setText(R.string.recover_training_explanation);
                lbl_advice_howto.setText(R.string.recover_training_howTo);

                // Style elements
                styleAdviceContainer(popupView, getResources().getColor(R.color.advice1));

                break;
            case R.id.btn_advice_2:
                lbl_advice_name.setText(R.string.slow_endurance_training_name);
                lbl_advice_mintime.setText(R.string.slow_endurance_training_minTime);
                lbl_advice_maxtime.setText(R.string.slow_endurance_training_maxTime);
                lbl_advice_minpercent.setText(R.string.slow_endurance_training_minPercentBPM);
                lbl_advice_maxpercent.setText(R.string.slow_endurance_training_maxPercentBPM);
                lbl_advice_goal.setText(R.string.slow_endurance_training_goal);
                lbl_advice_usage.setText(R.string.slow_endurance_training_usage);
                lbl_advice_frequency.setText(R.string.slow_endurance_training_frequency);
                lbl_advice_description.setText(R.string.slow_endurance_training_explanation);
                lbl_advice_howto.setText(R.string.slow_endurance_training_howTo);

                // Style elements
                styleAdviceContainer(popupView, getResources().getColor(R.color.advice2));

                break;
            case R.id.btn_advice_3:
                lbl_advice_name.setText(R.string.intensive_endurance_training_name);
                lbl_advice_mintime.setText(R.string.intensive_endurance_training_minTime);
                lbl_advice_maxtime.setText(R.string.intensive_endurance_training_maxTime);
                lbl_advice_minpercent.setText(R.string.intensive_endurance_training_minPercentBPM);
                lbl_advice_maxpercent.setText(R.string.intensive_endurance_training_maxPercentBPM);
                lbl_advice_goal.setText(R.string.intensive_endurance_training_goal);
                lbl_advice_usage.setText(R.string.intensive_endurance_training_usage);
                lbl_advice_frequency.setText(R.string.intensive_endurance_training_frequency);
                lbl_advice_description.setText(R.string.intensive_endurance_training_explanation);
                lbl_advice_howto.setText(R.string.intensive_endurance_training_howTo);

                // Style elements
                styleAdviceContainer(popupView, getResources().getColor(R.color.advice3));

                break;
            case R.id.btn_advice_4:
                lbl_advice_name.setText(R.string.Mlss_training_name);
                lbl_advice_mintime.setText(R.string.Mlss_training_minTime);
                lbl_advice_maxtime.setText(R.string.Mlss_training_maxTime);
                lbl_advice_minpercent.setText(R.string.Mlss_training_minPercentBPM);
                lbl_advice_maxpercent.setText(R.string.Mlss_training_maxPercentBPM);
                lbl_advice_goal.setText(R.string.Mlss_training_goal);
                lbl_advice_usage.setText(R.string.Mlss_training_usage);
                lbl_advice_frequency.setText(R.string.Mlss_training_frequency);
                lbl_advice_description.setText(R.string.Mlss_training_explanation);
                lbl_advice_howto.setText(R.string.Mlss_training_howTo);

                // Style elements
                styleAdviceContainer(popupView, getResources().getColor(R.color.advice4));

                break;
            case R.id.btn_advice_5:
                lbl_advice_name.setText(R.string.resistance_training_name);
                lbl_advice_mintime.setText(R.string.resistance_training_minTime);
                lbl_advice_maxtime.setText(R.string.resistance_training_maxTime);
                lbl_advice_minpercent.setText(R.string.resistance_training_minPercentBPM);
                lbl_advice_maxpercent.setText(R.string.resistance_training_maxPercentBPM);
                lbl_advice_goal.setText(R.string.resistance_training_goal);
                lbl_advice_usage.setText(R.string.resistance_training_usage);
                lbl_advice_frequency.setText(R.string.resistance_training_frequency);
                lbl_advice_description.setText(R.string.resistance_training_explanation);
                lbl_advice_howto.setText(R.string.resistance_training_howTo);

                // Style elements
                styleAdviceContainer(popupView, getResources().getColor(R.color.advice5));

                break;
        }
    }

    private void styleAdviceContainer(View popupView, int color) {

        // get styling elements
        this.lbl_header_advice_name = (TextView) popupView.findViewById(R.id.lbl_advice_name);
        this.lbl_header_advice_mintime = (TextView) popupView.findViewById(R.id.lbl_header_advice_mintime);
        this.lbl_header_advice_maxtime = (TextView) popupView.findViewById(R.id.lbl_header_advice_maxtime);
        this.lbl_header_advice_minpercent = (TextView) popupView.findViewById(R.id.lbl_header_advice_minpercent);
        this.lbl_header_advice_maxpercent = (TextView) popupView.findViewById(R.id.lbl_header_advice_maxpercent);
        this.lbl_header_advice_goal = (TextView) popupView.findViewById(R.id.lbl_header_advice_goal);
        this.lbl_header_advice_usage = (TextView) popupView.findViewById(R.id.lbl_header_advice_usage);
        this.lbl_header_advice_frequency = (TextView) popupView.findViewById(R.id.lbl_header_advice_frequency);
        this.lbl_header_advice_description = (TextView) popupView.findViewById(R.id.lbl_header_advice_description);
        this.lbl_header_advice_howto = (TextView) popupView.findViewById(R.id.lbl_header_advice_howto);
        this.advice_popup_background = (LinearLayout) popupView.findViewById(R.id.adviceContainer);

        // set styling elements
        lbl_advice_mintime.setTextColor(color);
        lbl_header_advice_mintime.setTextColor(color);
        lbl_advice_maxtime.setTextColor(color);
        lbl_header_advice_maxtime.setTextColor(color);
        lbl_advice_minpercent.setTextColor(color);
        lbl_header_advice_minpercent.setTextColor(color);
        lbl_advice_maxpercent.setTextColor(color);
        lbl_header_advice_maxpercent.setTextColor(color);
        lbl_advice_goal.setTextColor(color);
        lbl_header_advice_goal.setTextColor(color);
        lbl_advice_usage.setTextColor(color);
        lbl_header_advice_usage.setTextColor(color);
        lbl_advice_frequency.setTextColor(color);
        lbl_header_advice_frequency.setTextColor(color);
        lbl_advice_description.setTextColor(color);
        lbl_header_advice_description.setTextColor(color);
        lbl_advice_howto.setTextColor(color);
        lbl_header_advice_howto.setTextColor(color);
        advice_popup_background.setBackgroundColor(color);

    }

}
