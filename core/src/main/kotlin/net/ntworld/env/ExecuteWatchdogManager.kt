package net.ntworld.env

import org.apache.commons.exec.*
import java.io.ByteArrayOutputStream
import java.nio.file.Paths

internal object ExecuteWatchdogManager {
    private val watchdogs = mutableMapOf<String, ExecuteWatchdog>()

    fun makeWatchdog(id: String, timeout: Long = -1) {
        val watchdog = watchdogs[id]
        if (null !== watchdog) {
            return
        }
        val newWatchdog = ExecuteWatchdog(timeout)
        watchdogs[id] = newWatchdog
    }

    fun destroyProcessWatchdog(id: String) {
        val watchdog = watchdogs.remove(id)
        if (null !== watchdog && watchdog.isWatching) {
            watchdog.destroyProcess()
        }
    }

    fun isRunning(watchdogId: String): Boolean {
        val watchdog = watchdogs[watchdogId]
        return null !== watchdog
    }

    fun removeWatchdogWhenProcessExecuted(watchdogId: String?) {
        if (null !== watchdogId) {
            watchdogs.remove(watchdogId)
        }
    }

    fun assignWatchdogToExecutor(executor: Executor, watchdogId: String?) {
        if (null === watchdogId) {
            return
        }
        val watchdog = watchdogs[watchdogId]
        if (null !== watchdog) {
            executor.watchdog = watchdog
        }
    }
}