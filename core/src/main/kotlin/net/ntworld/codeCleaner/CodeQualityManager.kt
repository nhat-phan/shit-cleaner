package net.ntworld.codeCleaner

import net.ntworld.codeCleaner.structure.CodeQuality

object CodeQualityManager {
    private val data: MutableMap<String, CodeQuality> = mutableMapOf()

    fun create(id: String, raw: String) {

    }

    fun find(id: String): CodeQuality {
        return data[id]!!
    }
}