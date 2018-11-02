package com.ieeevit.evento.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.view.View;
import android.widget.RemoteViews;

import com.ieeevit.evento.R;
import com.ieeevit.evento.activities.WelcomeActivity;

/**
 * Implementation of App Widget functionality.
 */
public class EventoAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Details", Context.MODE_PRIVATE);
        boolean inEvent = sharedPreferences.getBoolean("InEvent", false);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.evento_app_widget);
        Intent homeIntent = new Intent(context, WelcomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, homeIntent, 0);
        views.setOnClickPendingIntent(R.id.widget_container, pendingIntent);

        if (inEvent) {
            Intent intent = new Intent(context, TimelineGridWidgetService.class);
            views.setRemoteAdapter(R.id.lv_event_timeline, intent);
            views.setViewVisibility(R.id.tv_event_missing, View.GONE);
            views.setViewVisibility(R.id.lv_event_timeline, View.VISIBLE);
        }
        else {
            views.setViewVisibility(R.id.lv_event_timeline, View.GONE);
            views.setViewVisibility(R.id.tv_event_missing, View.VISIBLE);
        }
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
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

