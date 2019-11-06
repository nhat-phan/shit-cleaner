package net.ntworld.intellijCodeCleaner.data

import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.ISSUE_NODE_TYPE_DIRECTORY
import net.ntworld.intellijCodeCleaner.ISSUE_NODE_TYPE_FILE
import net.ntworld.intellijCodeCleaner.ISSUE_NODE_TYPE_ROOT
import net.ntworld.intellijCodeCleaner.data.internal.IssueNodeImpl
import java.io.File

open class IssueNodeBuilder(root: IssueNode? = null) {
    internal val rootNode: IssueNode = if (null !== root) root else {
        IssueNodeImpl(
            type = ISSUE_NODE_TYPE_ROOT,
            name = File.separator
        )
    }

    open fun add(issue: Issue) {
        appendPathComponentsToTree(issue.path.split(File.separator))
    }

    internal fun appendPathComponentsToTree(components: List<String>) {
        var upper = rootNode
        components.forEachIndexed { index, name ->
            val node = if (index != components.lastIndex) {
                IssueNodeImpl(type = ISSUE_NODE_TYPE_DIRECTORY, name = name)
            } else {
                IssueNodeImpl(type = ISSUE_NODE_TYPE_FILE, name = name)
            }

            if (upper.children.isEmpty()) {
                upper.add(node)
            } else {
                val child = upper.children.firstOrNull {
                    it.type == node.type && it.name == node.name
                }
                if (null === child) {
                    upper.add(node)
                } else {
                    upper = child
                    return@forEachIndexed
                }
            }

            upper = node
        }
    }

    open fun build(): IssueNode {
        TODO()
    }

}