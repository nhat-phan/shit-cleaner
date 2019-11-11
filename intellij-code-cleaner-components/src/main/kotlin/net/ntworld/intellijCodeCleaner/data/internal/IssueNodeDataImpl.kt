package net.ntworld.intellijCodeCleaner.data.internal

import net.ntworld.intellijCodeCleaner.data.IssueNodeData

data class IssueNodeDataImpl(
    override val type: String,
    override val name: String = "",
    override val projectId: String = "",
    override val issueId: String = ""
) : IssueNodeData {
    override val value: MutableMap<String, Any> = mutableMapOf()
    override val text: MutableMap<String, String> = mutableMapOf()
    override val children: MutableList<IssueNodeData> = mutableListOf()

    override fun <T : Any> setValue(key: String, value: T) {
        this.value[key] = value
    }

    override fun setText(key: String, value: String) {
        text[key] = value
    }

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