/**
 * @file ChartActivity.java
 *
 * @brief      Az diagramot megjelenítő képernyő.
 * @details    Amennyiben érkezik adat a webszolgáltatástól, ezen a képernyőn jelenik meg grafikonon.
 */
package eu.ablonczy.csaba.variometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;

/**
 * Amennyiben &eacute;rkezik adat a webszolg&aacute;ltat&aacute;st&oacute;l, ezen a k&eacute;perny&#x151;n jelenik meg grafikonon.
 *
 * P&eacute;lda k&oacute;d:
 * @code
 *     Intent myIntent = new Intent(this, ChartActivity.class);
 *     this.startActivity(myIntent);
 * @endcode
 */
public class ChartActivity extends AppCompatActivity {

    private View mChart;
    private DateFormat df = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        openChart();

    }

    private void openChart(){
        // Creating an XYSeries for Altitude
        XYSeries altitudeSeries = new XYSeries("Altitude");
        // Adding data to Altitude Series
        int i = 0;
        for (MainActivity.FlyingResponse fr : MainActivity.flyingResponse) {
            altitudeSeries.add(i, fr.altitude);
            i++;
        }

        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding Altitude Series to the dataset
        dataset.addSeries(altitudeSeries);

        // Creating XYSeriesRenderer to customize altitudeSeries
        XYSeriesRenderer altitudeRenderer = new XYSeriesRenderer();
        altitudeRenderer.setColor(Color.RED); //color of the graph set to cyan
        altitudeRenderer.setFillPoints(true);
        altitudeRenderer.setLineWidth(2f);
        altitudeRenderer.setDisplayChartValues(false);
        //setting chart value distance
        altitudeRenderer.setDisplayChartValuesDistance(10);
        //setting line graph point style to circle
        altitudeRenderer.setPointStyle(PointStyle.POINT);
        //setting stroke of the line chart to solid
        altitudeRenderer.setStroke(BasicStroke.SOLID);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle(getString(R.string.title_activity_chart));
        multiRenderer.setXTitle(getString(R.string.Xtitle_activity_chart));
        multiRenderer.setYTitle(getString(R.string.Ytitle_activity_chart));

        /***
         * Customizing graphs
         */
        //setting text size of the title
        multiRenderer.setChartTitleTextSize(28);
        //setting text size of the axis title
        multiRenderer.setAxisTitleTextSize(24);
        //setting text size of the graph label
        multiRenderer.setLabelsTextSize(11);
        //setting zoom buttons visibility
        multiRenderer.setZoomButtonsVisible(true);
        //setting pan enabling which uses graph to move on both axis
        multiRenderer.setPanEnabled(true, true);
        //setting click false on graph
        multiRenderer.setClickEnabled(false);
        //setting zoom to false on both axis
        multiRenderer.setZoomEnabled(true, true);
        //setting lines to display on y axis
        multiRenderer.setShowGridY(true);
        //setting lines to display on x axis
        multiRenderer.setShowGridX(true);
        //setting legend to fit the screen size
        multiRenderer.setFitLegend(true);
        //setting displaying line on grid
        multiRenderer.setShowGrid(true);
        //setting zoom to false
        multiRenderer.setZoomEnabled(true);
        //setting external zoom functions to false
        multiRenderer.setExternalZoomEnabled(true);
        //setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer.setAntialiasing(true);
        //setting to in scroll to false
        multiRenderer.setInScroll(true);
        //setting to set legend height of the graph
        multiRenderer.setLegendHeight(30);
        //setting x axis label align
        multiRenderer.setXLabelsAlign(Align.RIGHT);
        //setting y axis label to align
        multiRenderer.setYLabelsAlign(Align.LEFT);
        //setting text style
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
        //setting no of values to display in y axis
        multiRenderer.setYLabels(25);
        // setting y axis max value, Since i'm using static values inside the graph so i'm setting y max value to 4000.
        // if you use dynamic values then get the max y value and set here
        multiRenderer.setYAxisMax(Collections.max(MainActivity.flyingResponse, new FlyingResponseComp()).altitude);

        multiRenderer.setXAxisMin(0);
        multiRenderer.setXAxisMax(MainActivity.flyingResponse.size());
        multiRenderer.setXLabelsAngle(270);

        //setting bar size or space between two bars
        //multiRenderer.setBarSpacing(0.5);
        //Setting background color of the graph to transparent
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
        //Setting margin color of the graph to transparent
        multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setScale(2f);
        //setting x axis point size
        multiRenderer.setPointSize(4f);
        //setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer.setMargins(new int[]{50, 30, 90, 10});

        i = 0;
        //String xValue;
        for(MainActivity.FlyingResponse fr : MainActivity.flyingResponse) {
            //xValue = this.df.format(fr.flyingDate);
            if((i % 50) == 0) {
                multiRenderer.addXTextLabel(i, this.df.format(fr.flyingDate));
            }
            i++;
        }
        multiRenderer.setAntialiasing(true);
        //multiRenderer.setAxesColor(Color.BLACK);

        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(altitudeRenderer);

        //this part is used to display graph on the xml
        LinearLayout chartContainer = (LinearLayout)findViewById(R.id.chart);
        //remove any views before painting the chart
        chartContainer.removeAllViews();
        //drawing bar chart
        mChart = ChartFactory.getLineChartView(this, dataset, multiRenderer);
        //adding the view to the linearlayout
        chartContainer.addView(mChart);
    }
}
