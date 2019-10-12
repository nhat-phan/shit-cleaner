package net.ntworld.env

object Env {
    private val osName = System.getProperty("os.name")

    fun isMac() = osName.contains("mac")

    fun isWindows() = osName.contains("win")
}