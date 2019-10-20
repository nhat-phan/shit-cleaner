package net.ntworld.intellijCodeCleaner.ui.overview.node

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.openapi.project.Project

class ProgressRootNode(project: Project) : AbstractTreeNode<String>(project, "Test") {

    override fun update(presentation: PresentationData) {

    }

    override fun getChildren(): MutableCollection<out AbstractTreeNode<Any>> {
        return mutableListOf()
    }

}