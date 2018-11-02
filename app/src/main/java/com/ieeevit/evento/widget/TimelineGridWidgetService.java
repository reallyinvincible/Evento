package com.ieeevit.evento.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.ieeevit.evento.R;
import com.ieeevit.evento.classes.Event;
import com.ieeevit.evento.classes.EventSession;

import java.util.List;

public class TimelineGridWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TimelineGridRemoteViewsFactory(this.getApplicationContext());
    }
}

class TimelineGridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context mContext;
    private final List<EventSession> eventSessionList;

    TimelineGridRemoteViewsFactory(Context mContext) {
        this.mContext = mContext;
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Details", Context.MODE_PRIVATE);
        String eventString = sharedPreferences.getString("EventDetails", "eventNotFound");
        Gson gson = new Gson();
        Event event = gson.fromJson(eventString, Event.class);
        eventSessionList = event.getEventSessions();
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return eventSessionList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        EventSession eventSession = eventSessionList.get(position);
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.grid_item_timeline);
        views.setTextViewText(R.id.tv_session_name, eventSession.getName());
        views.setTextViewText(R.id.tv_time, eventSession.getStartTime());
        views.setTextViewText(R.id.tv_date, eventSession.getDate());

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
