package net.ntworld.intellijCodeCleaner.component.overview

import com.intellij.ide.util.treeView.NodeRenderer
import com.intellij.openapi.project.Project
import com.intellij.ui.AnimatedIcon.ANIMATION_IN_RENDERER_ALLOWED
import com.intellij.ui.tree.AsyncTreeModel
import com.intellij.ui.tree.StructureTreeModel
import com.intellij.ui.treeStructure.Tree
import com.intellij.util.ui.UIUtil
import net.ntworld.intellijCodeCleaner.AppStore
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.component.overview.node.ProgressRootNode
import java.awt.Component
import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import javax.swing.tree.TreeCellRenderer

class OverviewProgress(
    private val project: Project,
    private val store: AppStore
) : TreeCellRenderer {

    private val tree = Tree()
    private val renderer = NodeRenderer()
    private val model = DefaultTreeModel(DefaultMutableTreeNode(ProgressRootNode(project, store.project)))

    val component: Tree = tree

    init {
        // val asyncTreeModel = AsyncTreeModel(model, project)
        // val structureTreeModel = StructureTreeModel(asyncTreeModel)

        tree.model = model
        tree.cellRenderer = this
        UIUtil.putClientProperty(tree, ANIMATION_IN_RENDERER_ALLOWED, true)
        store.onChange("project", this::onStoreChanged)
    }

    private fun makeRootTreeNode() {

    }

    private fun onStoreChanged() {
        println("changed, add new one")
        val root = model.root
        (root as DefaultMutableTreeNode).add(DefaultMutableTreeNode(ProgressRootNode(project, store.project)))
        model.setRoot(root)
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