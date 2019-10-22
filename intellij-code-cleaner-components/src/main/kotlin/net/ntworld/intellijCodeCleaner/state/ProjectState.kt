package net.ntworld.intellijCodeCleaner.state

data class ProjectState(
    val id: String,
    val initialized: Boolean
) {
    companion object {
        val Default = ProjectState(id = "", initialized = false)
    }
}
