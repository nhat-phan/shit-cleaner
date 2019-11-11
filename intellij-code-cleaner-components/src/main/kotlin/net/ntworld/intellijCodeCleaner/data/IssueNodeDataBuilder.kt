package net.ntworld.intellijCodeCleaner.data

import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.*
import net.ntworld.intellijCodeCleaner.data.internal.IssueNodeDataImpl
import java.io.File

open class IssueNodeDataBuilder(root: IssueNodeData? = null) {
    val rootNodeData: IssueNodeData = if (null !== root) root else {
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
        appendIssueToTree(node, issue, projectId, basePath)
    }

    open fun appendIssueToTree(parent: IssueNodeData, issue: Issue, projectId: String, basePath: String) {
        val base = if (basePath.isNotEmpty() && !basePath.endsWith(File.separatorChar)) {
            basePath + File.separatorChar
        } else {
            basePath
        }
        val issueNode = IssueNodeDataImpl(
            type = ISSUE_NODE_TYPE_ISSUE,
            name = issue.description,
            issueId = issue.id,
            projectId = projectId
        )
        issueNode.setValue(ISSUE_NODE_VALUE_PATH, base + issue.path)
        issueNode.setText(ISSUE_NODE_TEXT_LINE, makeLinesText(issue.lines.begin, issue.lines.end))

        for (location in issue.locations) {
            val node = IssueNodeDataImpl(
                type = ISSUE_NODE_TYPE_RELATED_ISSUE,
                name = location.path,
                issueId = issue.id,
                projectId = projectId
            )
            node.setValue(ISSUE_NODE_VALUE_PATH, base + location.path)
            node.setValue(ISSUE_NODE_VALUE_LINE_BEGIN, location.lines.begin)
            node.setValue(ISSUE_NODE_VALUE_LINE_END, location.lines.end)
            node.setText(ISSUE_NODE_TEXT_LINE, makeOnLinesText(location.lines.begin, location.lines.end))
            issueNode add node
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
                IssueNodeDataImpl(type = ISSUE_NODE_TYPE_DIRECTORY, name = name, projectId = projectId)
            } else {
                IssueNodeDataImpl(type = ISSUE_NODE_TYPE_FILE, name = name, projectId = projectId)
            }
            node.setValue(ISSUE_NODE_VALUE_PATH, findValueOfDirectoryOrFile(basePath, components, index))

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
            issueId = rootNodeData.issueId,
            projectId = rootNodeData.projectId
        )
        for (item in rootNodeData.text) {
            root.setText(item.key, item.value)
        }
        for (item in rootNodeData.value) {
            root.setValue(item.key, item.value)
        }
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
                projectId = node.projectId
            )
            for (item in node.value) {
                newNode.setValue(item.key, item.value)
            }
            for (item in node.children[0].children) {
                newNode.add(shortenSingleItemInDirectoryNode(item))
            }
            return shortenSingleItemInDirectoryNode(newNode)
        }
        return node
    }

}