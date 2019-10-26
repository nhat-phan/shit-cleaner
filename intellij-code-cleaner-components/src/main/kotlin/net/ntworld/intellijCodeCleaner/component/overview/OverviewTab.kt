package net.ntworld.intellijCodeCleaner.component.overview

import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.ui.ThreeComponentsSplitter
import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.ScrollPaneFactory
import net.ntworld.intellijCodeCleaner.ComponentFactory
import javax.swing.JPanel

open class OverviewTab(
    private val ideaProject: IdeaProject,
    private val toolWindow: ToolWindow,
    private val componentFactory: ComponentFactory
) {
    private val splitter = ThreeComponentsSplitter(false, true)
    private val overviewProgress by lazy {
        OverviewProgress(ideaProject, componentFactory.makeDispatcher().store.project)
    }

    fun createPanel(): JPanel {
        val dispatcher = componentFactory.makeDispatcher()
        val store = dispatcher.store
        store.onChange("project", this::updateOverviewProgress)

        splitter.firstComponent = ScrollPaneFactory.createScrollPane(overviewProgress.component)

        return splitter
    }

    private fun updateOverviewProgress() {
        overviewProgress.updateBy(componentFactory.makeDispatcher().store.project)
    }

}