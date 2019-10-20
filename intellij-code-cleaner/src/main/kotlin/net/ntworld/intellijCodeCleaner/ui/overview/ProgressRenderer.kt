package net.ntworld.intellijCodeCleaner.ui.overview

import com.intellij.ide.util.treeView.NodeRenderer
import java.awt.Component
import javax.swing.JTree
import javax.swing.tree.TreeCellRenderer

object ProgressRenderer : TreeCellRenderer {
    private val renderer = NodeRenderer()

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