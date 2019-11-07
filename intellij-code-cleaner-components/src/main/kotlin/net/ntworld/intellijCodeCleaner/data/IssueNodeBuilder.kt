package net.ntworld.intellijCodeCleaner.data

import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.*
import net.ntworld.intellijCodeCleaner.data.internal.IssueNodeDataImpl
import java.io.File

open class IssueNodeBuilder(root: IssueNodeData? = null) {
    internal val rootNodeData: IssueNodeData = if (null !== root) root else {
        IssueNodeDataImpl(
            type = ISSUE_NODE_TYPE_ROOT,
            name = ""
        )
    }

    open fun add(issue: Issue) {
        val node = appendPathComponentsToTree(issue.path.split(File.separator))
        appendIssueToTree(node, issue)
    }

    open fun appendIssueToTree(parent: IssueNodeData, issue: Issue) {
        val issueNode = IssueNodeDataImpl(
            type = ISSUE_NODE_TYPE_ISSUE,
            name = issue.description,
            value = makeLinesText(issue.lines.begin, issue.lines.end),
            id = issue.id
        )

        for (location in issue.locations) {
            issueNode add IssueNodeDataImpl(
                type = ISSUE_NODE_TYPE_RELATED_ISSUE,
                name = location.path,
                value = makeOnLinesText(location.lines.begin, location.lines.end),
                id = issue.id
            )
        }

        parent add issueNode
    }

    open fun makeLinesText(begin: Int, end: Int) = if (begin == end) {
        "Line $begin"
    } else {
        "Lines $begin..$end"
    }

    open fun makeOnLinesText(begin: Int, end: Int) = if (begin == end) {
        "on line $begin"
    } else {
        "on lines $begin..$end"
    }

    open fun appendPathComponentsToTree(components: List<String>): IssueNodeData {
        var upper = rootNodeData
        components.forEachIndexed { index, name ->
            val node = if (index != components.lastIndex) {
                IssueNodeDataImpl(type = ISSUE_NODE_TYPE_DIRECTORY, name = name)
            } else {
                IssueNodeDataImpl(type = ISSUE_NODE_TYPE_FILE, name = name)
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

    open fun build(): IssueNodeData {
        return rootNodeData
    }

}