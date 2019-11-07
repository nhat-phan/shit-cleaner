package net.ntworld.intellijCodeCleaner.data.internal

import net.ntworld.intellijCodeCleaner.data.IssueNodeData

internal data class IssueNodeDataImpl(
    override val type: String,
    override val name: String = "",
    override val value: String = "",
    override val projectId: String = "",
    override val issueId: String = ""
) : IssueNodeData {
    override val children: MutableList<IssueNodeData> = mutableListOf()

    override infix fun add(nodeData: IssueNodeData) {
        children.add(nodeData)
    }

    override infix fun remove(nodeData: IssueNodeData) {
        children.remove(nodeData)
    }

    override fun clear() {
        children.clear()
    }
}