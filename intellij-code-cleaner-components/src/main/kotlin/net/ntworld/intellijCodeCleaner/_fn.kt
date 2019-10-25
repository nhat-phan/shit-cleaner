package net.ntworld.intellijCodeCleaner

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

internal val DateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
internal val TimeFormatter = DateTimeFormat.forPattern("HH:mm")

fun DateTime.toDateTimeString(preposition: String = " "): String {
    return "${DateFormatter.print(this)}$preposition${TimeFormatter.print(this)}"
}