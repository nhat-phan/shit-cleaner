package net.ntworld.intellijCodeCleaner.data

import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.*
import net.ntworld.intellijCodeCleaner.data.internal.IssueNodeDataImpl
import java.io.File

open class IssueNodeDataBuilder(root: IssueNodeData? = null) {
    internal val rootNodeData: IssueNodeData = if (null !== root) root else {
        IssueNodeDataImpl(
            type = ISSUE_NODE_TYPE_ROOT,
            name = ""
        )
    }

    open fun add(issue: Issue, projectId: String = "", basePath: String = "") {
        val node = appendPathComponentsToTree(
            issue.path.split(File.separator),
            projectId,
            basePath
        )
        appendIssueToTree(node, issue)
    }

    open fun appendIssueToTree(parent: IssueNodeData, issue: Issue) {
        val issueNode = IssueNodeDataImpl(
            type = ISSUE_NODE_TYPE_ISSUE,
            name = issue.description,
            value = makeLinesText(issue.lines.begin, issue.lines.end),
            issueId = issue.id
        )

        for (location in issue.locations) {
            issueNode add IssueNodeDataImpl(
                type = ISSUE_NODE_TYPE_RELATED_ISSUE,
                name = location.path,
                value = makeOnLinesText(location.lines.begin, location.lines.end),
                issueId = issue.id
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

    open fun appendPathComponentsToTree(components: List<String>, projectId: String, basePath: String): IssueNodeData {
        var upper = rootNodeData
        components.forEachIndexed { index, name ->
            val node = if (index != components.lastIndex) {
                IssueNodeDataImpl(
                    type = ISSUE_NODE_TYPE_DIRECTORY, name = name, projectId = projectId,
                    value = findValueOfDirectoryOrFile(basePath, components, index)
                )
            } else {
                IssueNodeDataImpl(
                    type = ISSUE_NODE_TYPE_FILE, name = name, projectId = projectId,
                    value = findValueOfDirectoryOrFile(basePath, components, index)
                )
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

    open fun findValueOfDirectoryOrFile(basePath: String, components: List<String>, index: Int): String {
        val base = if (basePath.isNotEmpty() && !basePath.endsWith(File.separatorChar)) {
            basePath + File.separatorChar
        } else {
            basePath
        }
        val buffer = StringBuffer()
        buffer.append(base)
        for (i in 0..index) {
            buffer.append(components[i])
            if (i != index) {
                buffer.append(File.separatorChar)
            }
        }
        return buffer.toString()
    }

    open fun build(): IssueNodeData {
        val root = IssueNodeDataImpl(
            type = rootNodeData.type,
            name = rootNodeData.name,
            value = rootNodeData.value,
            issueId = rootNodeData.issueId,
            projectId = rootNodeData.projectId
        )
        for (node in rootNodeData.children) {
            root.add(shortenSingleItemInDirectoryNode(node))
        }
        return root
    }

    open fun shortenSingleItemInDirectoryNode(node: IssueNodeData): IssueNodeData {
        if (node.type == ISSUE_NODE_TYPE_DIRECTORY &&
            node.children.size == 1 && node.children[0].type == ISSUE_NODE_TYPE_DIRECTORY
        ) {
            val newNode = IssueNodeDataImpl(
                type = ISSUE_NODE_TYPE_DIRECTORY,
                name = node.name + File.separator + node.children[0].name,
                projectId = node.projectId,
                value = node.children[0].value
            )
            for (item in node.children[0].children) {
                newNode.add(shortenSingleItemInDirectoryNode(item))
            }
            return shortenSingleItemInDirectoryNode(newNode)
        }
        return node
    }

}