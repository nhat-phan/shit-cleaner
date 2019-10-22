package net.ntworld.intellijCodeCleaner.component.overview

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import net.ntworld.intellijCodeCleaner.ComponentFactory
import javax.swing.JPanel

open class OverviewTab(
    private val project: Project,
    private val toolWindow: ToolWindow,
    private val componentFactory: ComponentFactory
) {

    fun createPanel(): JPanel {
        return JPanel()
    }

}