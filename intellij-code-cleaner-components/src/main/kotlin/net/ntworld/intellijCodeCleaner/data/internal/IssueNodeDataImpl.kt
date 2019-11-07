package net.ntworld.intellijCodeCleaner.data.internal

import net.ntworld.intellijCodeCleaner.data.IssueNodeData

internal class IssueNodeDataImpl(
    override val type: String,
    override var name: String = "",
    override var value: String = "",
    override var id: String = ""
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