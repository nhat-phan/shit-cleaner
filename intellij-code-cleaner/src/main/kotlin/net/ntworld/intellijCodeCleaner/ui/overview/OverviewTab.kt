package net.ntworld.intellijCodeCleaner.ui.overview

import com.intellij.openapi.project.Project
import com.intellij.ui.OnePixelSplitter
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.treeStructure.Tree
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import net.ntworld.intellijCodeCleaner.ui.MainToolbar
import javax.swing.JPanel
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel

class OverviewTab(private val project: Project) {
    private val splitter = OnePixelSplitter(false, "code-cleaner.overview", 0.3f)
    private val overviewPanel = JPanel()
    private val progressTree = Tree()

    init {
        initProgressPanel()
        initOverviewPanel()

        splitter.firstComponent = ScrollPaneFactory.createScrollPane(progressTree)
        splitter.secondComponent = overviewPanel
    }

    private fun initProgressPanel() {
        // init progressTree
        val default = DefaultMutableTreeNode("Something")
        for (i in 1..50) {
            default.add(DefaultMutableTreeNode("Child $i"))
        }

        val model = DefaultTreeModel(default)

        progressTree.model = model
        progressTree.cellRenderer = ProgressRenderer
    }

    private fun initOverviewPanel() {
        overviewPanel.layout = GridLayoutManager(1, 1)
    }

    private fun makeConstrains(): GridConstraints {
        val constraint = GridConstraints()
        constraint.row = 0
        constraint.column = 0
        constraint.fill = GridConstraints.FILL_BOTH
        return constraint
    }

    fun getContent(): Content {
        return ContentFactory.SERVICE.getInstance().createContent(
            MainToolbar.apply(splitter), "Overview", false
        )
    }
}