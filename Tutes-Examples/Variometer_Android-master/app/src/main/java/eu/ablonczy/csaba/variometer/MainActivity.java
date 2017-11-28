/**
 * @file MainActivity.java
 *
 * @brief      Az indító képernyő.
 * @details    Az alkalmazás indításakor az ebben lévő MainActivity osztály példányosodik és fut le.
 */
package eu.ablonczy.csaba.variometer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
//import android.widget.Spinner;
//import android.widget.Toast;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.TimeUnit;


/**
 * Az alkalmaz&aacute;s ind&iacute;t&aacute;sakor ez az oszt&aacute;ly p&eacute;ld&aacute;nyosodik &eacute;s fut le.
 */
public class MainActivity extends AppCompatActivity {
    public class FlyingResponse {
        public Date flyingDate;
        public Date uploadDate;
        public Integer altitude;
        public Integer speedN;
    }

    public class VariometerType {
        public Integer placeID;
        public Date flyingDate;
    }

    public Boolean isCommSuccess = Boolean.FALSE;
    public Map<String, Integer> flyingPlaces = new HashMap<String, Integer>();
    public static Vector<FlyingResponse> flyingResponse = new Vector<FlyingResponse>();
    private Context _ctx;
    protected Button buttonQuery;
    protected ProgressBar pBar;

    @Override
    protected void onRestart() {
        super.onRestart();
        new WebServiceClient.GetPlaces().execute(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Spinner placeSpinner = (Spinner)findViewById(R.id.spinnerPlace);

        _ctx = this;
        buttonQuery = (Button)findViewById(R.id.buttonQuery);
        pBar = (ProgressBar)findViewById(R.id.progressBar);


        InternetConnectionDetector cd =
                new InternetConnectionDetector(getApplicationContext());

        if (!cd.checkInternetConn()) {
            MessageBox<Integer> mb = new MessageBox<>();
            mb.show(this, R.string.No_Connection_Title, R.string.No_Connection_Msg, true);
        } else {
            new WebServiceClient.GetPlaces().execute(this);
        }
    }

    public void buttonClicked(View v) {
        if ( ((Spinner) findViewById(R.id.spinnerPlace)).getSelectedItem() == null ) {
            return;
        }

        DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);

        GregorianCalendar gc = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        gc.setTimeZone(TimeZone.getTimeZone("UTC"));
        VariometerType vt = new VariometerType();
        vt.placeID = flyingPlaces.get(((Spinner) findViewById(R.id.spinnerPlace)).getSelectedItem().toString());
        vt.flyingDate = gc.getTime();
        isCommSuccess = Boolean.FALSE;
        this.buttonQuery.setEnabled(Boolean.FALSE);
        this.pBar.setVisibility(View.VISIBLE);
        WebServiceClient.GetFlyings task = new WebServiceClient.GetFlyings(vt); //.execute(_ctx);
        task.execute(_ctx);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent myIntent = new Intent(this, UserSettingsActivity.class);
                this.startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
