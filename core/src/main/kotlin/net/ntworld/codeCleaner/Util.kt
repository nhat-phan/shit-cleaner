package net.ntworld.codeCleaner

import org.joda.time.DateTime
import org.joda.time.DateTimeZone

object Util {
    fun utcNow(): String {
        return DateTime.now(DateTimeZone.UTC).toString()
    }
}