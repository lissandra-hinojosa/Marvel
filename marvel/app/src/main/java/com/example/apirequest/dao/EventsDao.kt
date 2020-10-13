package com.example.apirequest.dao

import android.provider.ContactsContract
import com.example.apirequest.models.event.Event

class EventsDao private constructor(val status: Status, val comics: List<Event>?, val error: String?) {
    companion object {
        fun success(events: List<Event>) = EventsDao(Status.SUCCESS, events, null)
        fun error(error: String) = EventsDao(Status.ERROR, null, error)
    }
}