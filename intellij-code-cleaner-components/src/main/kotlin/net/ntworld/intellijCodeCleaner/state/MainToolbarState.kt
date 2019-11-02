package net.ntworld.intellijCodeCleaner.state

data class MainToolbarState(
    val analyzing: Boolean,
    val filteringByBadIssues: Boolean,
    val filteringByModerateIssues: Boolean,
    val filteringByGoodIssues: Boolean,
    val openingAnnotations: Boolean
) {

    companion object {
        val Default = MainToolbarState(
            analyzing = false,
            filteringByBadIssues = true,
            filteringByModerateIssues = true,
            filteringByGoodIssues = true,
            openingAnnotations = true
        )
    }
}