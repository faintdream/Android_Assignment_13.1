package com.akashdubey.widgetdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by FLAdmin on 2/7/2018.
 */

public class WidgetDemo extends AppWidgetProvider {

    //This method is called in response to the ACTION_APPWIDGET_UPDATE  from
    // the manifest(basically in response to  broadcast) when this AppWidget provider is being asked to provide RemoteViews for a set of AppWidgets.
    // We Override this method to implement own AppWidget functionality.
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Toast.makeText(context, "Widget Added/Updated", Toast.LENGTH_LONG).show();
        String url="http://www.acadgild.com";

        for (int item=0;item<appWidgetIds.length;item++){
            int currentId=appWidgetIds[item];
            Intent intent=new Intent(Intent.ACTION_VIEW);

            //letting know the system that we would like this intent to be part of task Builder
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(url));

            //setting up pending intent
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);

            //setting up RemoteViews which will be used with pending intent
            RemoteViews remoteViews=new RemoteViews(context.getPackageName(),R.layout.widget_main);

            //passing the id of button to pendingIntent's OnClick event
            remoteViews.setOnClickPendingIntent(R.id.goBtn,pendingIntent);

            // what is AppWidgetmanager?
            //Updates AppWidget state; gets information about installed AppWidget providers and other AppWidget related state.

            //what is updateAppWidget?
            //Set the RemoteViews to use for all AppWidget instances for the supplied AppWidget provider.
            appWidgetManager.updateAppWidget(currentId,remoteViews);
            Toast.makeText(context, "Widget added successfully", Toast.LENGTH_SHORT).show();

        }
    }
}
