package net.ntworld.intellijCodeCleaner.component.overview

import com.intellij.ide.util.treeView.NodeRenderer
import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.ui.AnimatedIcon.ANIMATION_IN_RENDERER_ALLOWED
import com.intellij.ui.treeStructure.Tree
import com.intellij.util.ui.UIUtil
import net.ntworld.intellijCodeCleaner.component.overview.node.ProgressAnalyzeNode
import net.ntworld.intellijCodeCleaner.component.overview.node.ProgressRootNode
import net.ntworld.intellijCodeCleaner.component.overview.node.ProgressStatisticNode
import net.ntworld.intellijCodeCleaner.state.ProjectState
import java.awt.Component
import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import javax.swing.tree.TreeCellRenderer

class OverviewProgress(
    private val ideaProject: IdeaProject,
    prop: ProjectState
) : TreeCellRenderer {

    private val tree = Tree()
    private val renderer = NodeRenderer()
    private val model = DefaultTreeModel(makeRootTreeNode(prop))

    val component: Tree = tree

    init {
        tree.model = model
        tree.cellRenderer = this
        UIUtil.putClientProperty(tree, ANIMATION_IN_RENDERER_ALLOWED, true)
    }

    fun updateBy(prop: ProjectState) {
        model.setRoot(makeRootTreeNode(prop))
    }

    private fun makeRootTreeNode(prop: ProjectState): DefaultMutableTreeNode {
        val root = DefaultMutableTreeNode(ProgressRootNode(ideaProject, prop))
        if (null !== prop.time) {
            root.add(DefaultMutableTreeNode(ProgressStatisticNode(ideaProject, prop)))
            root.add(DefaultMutableTreeNode(ProgressAnalyzeNode(ideaProject, prop)))
        }
        return root
    }

    override fun getTreeCellRendererComponent(
        tree: JTree?,
        value: Any?,
        selected: Boolean,
        expanded: Boolean,
        leaf: Boolean,
        row: Int,
        hasFocus: Boolean
    ): Component {
        return renderer.getTreeCellRendererComponent(
            tree,
            value,
            selected,
            expanded,
            leaf,
            row,
            hasFocus
        )
    }
}