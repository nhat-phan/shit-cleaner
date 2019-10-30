package net.ntworld.intellijCodeCleaner.component.issue

import com.intellij.ide.util.treeView.NodeRenderer
import com.intellij.openapi.project.Project
import com.intellij.ui.treeStructure.Tree
import net.ntworld.codeCleaner.structure.Issue
import java.awt.Component
import javax.swing.JTree
import javax.swing.tree.TreeCellRenderer

open class IssueTree(
    private val ideaProject: Project,
    data: List<Issue>
): TreeCellRenderer {
    private val tree = Tree()
    private val renderer = NodeRenderer()

    val component: Tree = tree

    init {
        tree.cellRenderer = this
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