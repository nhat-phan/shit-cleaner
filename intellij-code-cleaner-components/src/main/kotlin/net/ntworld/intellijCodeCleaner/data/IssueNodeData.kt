package net.ntworld.intellijCodeCleaner.data

interface IssueNodeData {
    val issueId: String

    val projectId: String

    val type: String

    val name: String

    val value: Map<String, Any>

    val text: Map<String, String>

    val children: List<IssueNodeData>

    fun <T : Any> setValue(key: String, value: T)

    fun setText(key: String, value: String)

    infix fun add(nodeData: IssueNodeData)

    infix fun remove(nodeData: IssueNodeData)

    fun clear()
}