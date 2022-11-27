package com.example.widgetapp5;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link  }
 */
public class MyAppWidget extends AppWidgetProvider {
    public static RemoteViews views;
    static int count = 0;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        views.setTextViewText(R.id.app_widget_text_id, widgetText);
        Log.e("stalo się ------", "jest ");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.pja.edu.pl"));
        PendingIntent pendingIntent = PendingIntent
                .getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.buttonLink, pendingIntent);

        Intent intent1 = new Intent(context, MyAppWidget.class);
        intent1.setAction("zmiana");
        PendingIntent pendingIntent1 = PendingIntent
                .getBroadcast(context, 0, intent1, 0);
        views.setOnClickPendingIntent(R.id.buttonImagePrev, pendingIntent1);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        Log.i("Widget_My", "First widget enabled");
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        Log.i("Widget_My", "Last widget disabled");

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals("zmiana")) {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
            ComponentName appWidgetId = new ComponentName(context, MyAppWidget.class);

            if (count % 2 == 0) {
                views.setImageViewResource(R.id.imageView, R.drawable.aa2);
                Log.e("bb2", "obraz2--------------------------------------");
            } else {
                views.setImageViewResource(R.id.imageView, R.drawable.aa1);
                Log.e("bb1", "obraz1--------------------------------------");
            }
            count++;
            Toast.makeText(context, "zmiana zdjęcia", Toast.LENGTH_SHORT).show();
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

}

