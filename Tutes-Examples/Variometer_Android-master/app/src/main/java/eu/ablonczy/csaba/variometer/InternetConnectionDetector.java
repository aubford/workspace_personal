package eu.ablonczy.csaba.variometer;

/**
 * Created by Csabi on 2015.10.01..
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnectionDetector {

    private Context _context;

    public InternetConnectionDetector(Context context) {
        this._context = context;
    }

    public boolean checkInternetConn() {
        //Create object for ConnectivityManager class which returns network related info
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //If connectivity object is not null
        if (connectivity != null) {
            //Get network info - Active internet access
            NetworkInfo info = connectivity.getActiveNetworkInfo(); //  getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (info != null && info.isConnected()) {
                return true;
            }
        }
        return false;
    }
}
