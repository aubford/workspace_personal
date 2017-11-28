package eu.ablonczy.csaba.variometer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Csabi on 2015.10.01..
 */

public class MessageBox <T> {

    public void show(final Context context, T title, T message, final Boolean shouldExit) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (title instanceof Integer) {
            // Setting Dialog Title
            builder.setTitle(context.getResources().getString((Integer)title));

            // Setting Dialog Message
            builder.setMessage(context.getResources().getString((Integer)message));
        }
        else if (title instanceof String) {
            // Setting Dialog Title
            builder.setTitle((String)title);

            // Setting Dialog Message
            builder.setMessage((String)message);
        }
        else {
            return;
        }
        // Setting alert dialog icon
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setCancelable(false);

        // Setting OK Button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if ( shouldExit ) {
                    System.exit(0);
                }
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        // Showing Alert Message
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
