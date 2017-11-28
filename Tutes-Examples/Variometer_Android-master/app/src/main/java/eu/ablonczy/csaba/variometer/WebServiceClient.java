package eu.ablonczy.csaba.variometer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.preference.PreferenceManager;
import android.widget.Toast;
//import android.widget.Toast;
//import org.json.JSONArray;
//import org.json.JSONObject;
import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
//import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Csabi on 2015.09.29..
 */
public class WebServiceClient {

    public static class GetPlaces extends AsyncTask<Context, String, Context> {
        private final String NAMESPACE = "http://tempuri.org/"; //com.service.Service
        private final String SOAP_ACTION = "http://tempuri.org/IService/"; //Service | http://csaba.ablonczy.eu/";
        private final String METHOD_NAME = "GetPlaces";
        private Context _ctx;


        private class GetPlacesResponse {
            public String placeName;
            public Integer placeID;
        }

        private String[] arraySpinner;
        private SoapPrimitive response;
        private GetPlacesResponse[] resArray;

        @Override
        protected Context doInBackground(Context... params) {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            _ctx = params[0];
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(_ctx);
            final String URL = prefs.getString("ws_address", "");

            SoapSerializationEnvelopeMod envelope = new SoapSerializationEnvelopeMod(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.setAddAdornments(false);
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.debug = true;

            try {
                androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope); // + webMethName
                response = (SoapPrimitive)envelope.getResponse();
                GsonBuilder gsonb = new GsonBuilder();
                Gson gson = gsonb.create();

                resArray = gson.fromJson(response.toString(), GetPlacesResponse[].class);
            } catch (Exception e) {
                publishProgress(e.getLocalizedMessage());
            } finally {
                return params[0];
            }
        }

        protected void onProgressUpdate(String message) {
            new MessageBox<String>().show(_ctx, "", message, Boolean.FALSE);
            return;
        }

        protected void onPostExecute(Context ctx) {
            if(resArray == null) {
                new MessageBox<Integer>().show(ctx, R.string.Ws_failure_title, R.string.Ws_failure, false);
                return;
            }

            Spinner placeSpinner = (Spinner)((Activity)ctx).findViewById(R.id.spinnerPlace);

            arraySpinner = new String[resArray.length];
            ((MainActivity)ctx).flyingPlaces.clear();
            int i = 0;
            for (GetPlacesResponse s : resArray) {
                ((MainActivity)ctx).flyingPlaces.put(s.placeName, s.placeID);
                arraySpinner[i] = s.placeName;
                i++;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, R.layout.my_spinner_item , arraySpinner);
            placeSpinner.setAdapter(adapter);
            ((MainActivity)ctx).isCommSuccess = Boolean.TRUE;
        }
    }


    public static class GetFlyings extends AsyncTask<Context, String, Context> {
        private final String NAMESPACE = "http://tempuri.org/"; //com.service.Service
        private final String SOAP_ACTION = "http://tempuri.org/IService/"; //Service | http://csaba.ablonczy.eu/";
        private final String METHOD_NAME = "GetFlyingByPlaceAndDate";

        private String[] arraySpinner;
        private SoapPrimitive response;
        private MainActivity.FlyingResponse[] resArray;
        private MainActivity.VariometerType reqData;
        private Context _ctx;

        GetFlyings(MainActivity.VariometerType requestData) {
            reqData = requestData;
        }

        @Override
        protected Context doInBackground(Context... params) {
            _ctx = params[0];
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(_ctx);
            final String URL = prefs.getString("ws_address", "");
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            PropertyInfo fd = new PropertyInfo();
            fd.setName("FlyingDate");
            fd.setValue(reqData.flyingDate);
            fd.setType(Date.class);

            PropertyInfo pID = new PropertyInfo();
            pID.setName("PlaceID");
            pID.setValue(reqData.placeID);
            pID.setType(Integer.class);

            request.addProperty(fd);
            request.addProperty(pID);

            SoapSerializationEnvelopeMod envelope = new SoapSerializationEnvelopeMod(SoapEnvelope.VER11);
            envelope.dotNet = true;
//            envelope.encodingStyle = null; //SoapSerializationEnvelope.ENC;
            envelope.setAddAdornments(false);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
//            envelope.headerOut = headerElems;
//            envelope.bodyOut = request;

            HttpTransportSEMod androidHttpTransport = new HttpTransportSEMod(URL);
            androidHttpTransport.debug = true;
            new MarshalDate().register(envelope);

            try {
                androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope); // , headerProperty //+ webMethName
                response = (SoapPrimitive)envelope.getResponse();
                GsonBuilder gsonb = new GsonBuilder();
                Gson gson = gsonb.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.FFFFFFF").create();

                resArray = gson.fromJson(response.toString(), MainActivity.FlyingResponse[].class);
            } catch (Exception e) {
                publishProgress(e.getLocalizedMessage());
            } finally {
                return params[0];
            }
        }

        protected void onProgressUpdate(String message) {
            new MessageBox<String>().show(_ctx, "", message, Boolean.FALSE);
            return;
        }

        protected void onPostExecute(Context ctx) {
            ((MainActivity)ctx).isCommSuccess = Boolean.FALSE;
            ((MainActivity)ctx).buttonQuery.setEnabled(Boolean.TRUE);
            ((MainActivity)ctx).pBar.setVisibility(View.INVISIBLE);
            if(resArray == null) {
                new MessageBox<Integer>().show(ctx, R.string.Ws_failure_title, R.string.Ws_failure, false);
                return;
            }

            if(resArray.length == 0) {
                Toast.makeText(ctx, R.string.Ws_nodata, Toast.LENGTH_SHORT).show();
                return;
            }
            MainActivity.flyingResponse.clear();
            MainActivity.flyingResponse.addAll(Arrays.asList(resArray));

            ((MainActivity)ctx).isCommSuccess = Boolean.TRUE;
            Intent myIntent = new Intent(ctx, ChartActivity.class);
            //myIntent.putExtra("flyingResponses", ((MainActivity)ctx).flyingResponse);
            ctx.startActivity(myIntent);
        }
    }
}
