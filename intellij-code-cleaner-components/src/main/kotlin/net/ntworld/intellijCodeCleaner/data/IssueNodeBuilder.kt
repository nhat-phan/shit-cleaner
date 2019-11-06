package net.ntworld.intellijCodeCleaner.data

import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.*
import net.ntworld.intellijCodeCleaner.data.internal.IssueNodeImpl
import java.io.File

open class IssueNodeBuilder(root: IssueNode? = null) {
    internal val rootNode: IssueNode = if (null !== root) root else {
        IssueNodeImpl(
            type = ISSUE_NODE_TYPE_ROOT,
            name = ""
        )
    }

    open fun add(issue: Issue) {
        val node = appendPathComponentsToTree(issue.path.split(File.separator))
        appendIssueToTree(node, issue)
    }

    open fun appendIssueToTree(parent: IssueNode, issue: Issue) {
        val issueNode = IssueNodeImpl(
            type = ISSUE_NODE_TYPE_ISSUE,
            name = issue.description,
            value = makeLinesText(issue.lines.begin, issue.lines.end),
            id = issue.id
        )

        for (location in issue.locations) {
            issueNode add IssueNodeImpl(
                type = ISSUE_NODE_TYPE_RELATED_ISSUE,
                name = location.path,
                value = makeLinesText(location.lines.begin, location.lines.end),
                id = issue.id
            )
        }

        parent add issueNode
    }

    open fun makeLinesText(begin: Int, end: Int) = if (begin == end) {
        "on line $begin"
    } else {
        "on lines $begin..$end"
    }

    open fun appendPathComponentsToTree(components: List<String>): IssueNode {
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
        return upper
    }

    open fun build(): IssueNode {
        return rootNode
    }

}