package com.ieeevit.evento.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "scanned_events")
public class ScannedEvent {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "event_id")
    private String eventId;
    @ColumnInfo(name = "event_name")
    private String eventName;
    @ColumnInfo(name = "hosting_organisation")
    private String hostingOrganisation;

    public ScannedEvent(String eventId, String eventName, String hostingOrganisation) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.hostingOrganisation = hostingOrganisation;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getHostingOrganisation() {
        return hostingOrganisation;
    }

    public void setHostingOrganisation(String hostingOrganisation) {
        this.hostingOrganisation = hostingOrganisation;
    }
}
