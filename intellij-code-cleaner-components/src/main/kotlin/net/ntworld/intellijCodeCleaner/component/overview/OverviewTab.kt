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
    protected open val overviewProgress = OverviewProgress(ideaProject, componentFactory.makeDispatcher().store.project)
    protected open val overviewTable = OverviewTable(componentFactory.makeDispatcher().store.project)

    open fun createPanel(): JPanel {
        val dispatcher = componentFactory.makeDispatcher()
        val store = dispatcher.store
        store.onChange("project", this::updateComponents)

        splitter.firstComponent = ScrollPaneFactory.createScrollPane(overviewProgress.component)
        splitter.innerComponent = ScrollPaneFactory.createScrollPane(overviewTable.component)

        splitter.firstSize = 400
        splitter.lastSize = 300
        return splitter
    }

    protected open fun updateComponents() {
        overviewTable.updateBy(componentFactory.makeDispatcher().store.project)
        overviewProgress.updateBy(componentFactory.makeDispatcher().store.project)
    }

}