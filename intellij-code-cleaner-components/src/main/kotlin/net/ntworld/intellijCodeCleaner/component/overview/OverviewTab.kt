package net.ntworld.intellijCodeCleaner.component.overview

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ThreeComponentsSplitter
import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.ScrollPaneFactory
import net.ntworld.intellijCodeCleaner.ComponentFactory
import javax.swing.JPanel

open class OverviewTab(
    private val project: Project,
    private val toolWindow: ToolWindow,
    private val componentFactory: ComponentFactory
) {
    private val splitter = ThreeComponentsSplitter(false, true)

    fun createPanel(): JPanel {
        val dispatcher = componentFactory.makeDispatcher()
        splitter.firstComponent = ScrollPaneFactory.createScrollPane(
            OverviewProgress(project, dispatcher.store).component
        )

        return splitter
    }

}