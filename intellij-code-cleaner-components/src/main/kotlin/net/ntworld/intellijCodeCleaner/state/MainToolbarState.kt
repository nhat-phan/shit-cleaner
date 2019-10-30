package net.ntworld.intellijCodeCleaner.state

data class MainToolbarState(
    val analyzing: Boolean,
    val hasData: Boolean,
    val hasBadIssues: Boolean,
    val hasModerateIssues: Boolean,
    val hasGoodIssues: Boolean,
    val filteringByBadIssues: Boolean,
    val filteringByModerateIssues: Boolean,
    val filteringByGoodIssues: Boolean,
    val openingAnnotations: Boolean
) {

    companion object {
        val Default = MainToolbarState(
            analyzing = false,
            hasData = false,
            hasBadIssues = false,
            hasModerateIssues = false,
            hasGoodIssues = false,
            filteringByBadIssues = true,
            filteringByModerateIssues = true,
            filteringByGoodIssues = true,
            openingAnnotations = true
        )
    }
}