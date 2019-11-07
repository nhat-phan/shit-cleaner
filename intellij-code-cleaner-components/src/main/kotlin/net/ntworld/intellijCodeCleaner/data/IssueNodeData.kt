package net.ntworld.intellijCodeCleaner.data

interface IssueNodeData {
    val issueId: String

    val projectId: String

    val type: String

    val name: String

    val value: String

    val children: List<IssueNodeData>

    infix fun add(nodeData: IssueNodeData)

    infix fun remove(nodeData: IssueNodeData)

    fun clear()
}