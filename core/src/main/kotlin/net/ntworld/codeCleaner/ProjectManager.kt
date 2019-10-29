package net.ntworld.codeCleaner

import net.ntworld.codeCleaner.structure.Project

object ProjectManager {
    private var data: Project? = null

    fun create(id: String, name: String, path: String) {
        data = Project.make(
            id = id,
            name = name,
            path = path
        )
    }

    fun find(id: String): Project {
        return data!!
    }
}