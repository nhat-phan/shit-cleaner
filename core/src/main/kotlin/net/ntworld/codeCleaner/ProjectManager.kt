package net.ntworld.codeCleaner

import net.ntworld.codeCleaner.structure.Project

object ProjectManager {
    private val data: MutableMap<String, Project> = mutableMapOf()

    fun create(id: String, name: String, path: String) {
        data[id] = Project.make(
            id = id,
            name = name,
            path = path
        )
    }

    fun find(id: String): Project {
        return data[id]!!
    }
}