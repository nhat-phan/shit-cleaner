package net.ntworld.intellijCodeCleaner

import com.intellij.openapi.vfs.VirtualFile
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.io.BufferedReader
import java.io.InputStreamReader

internal val DateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
internal val TimeFormatter = DateTimeFormat.forPattern("HH:mm")

fun DateTime.toDateTimeString(preposition: String = " "): String {
    return "${DateFormatter.print(this)}$preposition${TimeFormatter.print(this)}"
}

fun VirtualFile.readContent(): String {
    val reader = InputStreamReader(this.inputStream, this.charset)
    reader.use {
        return reader.readText()
    }
}

fun VirtualFile.readLines(): List<String> {
    val reader = BufferedReader(InputStreamReader(this.inputStream, this.charset))
    val result = mutableListOf<String>()
    reader.use {
        val iterator = reader.lineSequence().iterator()
        while (iterator.hasNext()) {
            result.add(iterator.next())
        }
    }
    return result
}