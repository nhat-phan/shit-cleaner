package net.ntworld.intellijCodeCleaner.component.util

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object Icons {
    private fun load(path: String): Icon? {
        return IconLoader.findIcon(path, Icons::class.java)
    }

    val Analyze = load("/icons/analyze-action.svg")
    val Stop = load("/icons/stop-analyze-action.svg")
    val ShowAnnotation = load("/icons/show-annotation-action.svg")
    val MaintainabilityFilterGood =
        load("/icons/issue-file-filter-a.svg")
    val MaintainabilityFilterModerate =
        load("/icons/issue-file-filter-c.svg")
    val MaintainabilityFilterBad =
        load("/icons/issue-file-filter-f.svg")
}