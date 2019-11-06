package net.ntworld.intellijCodeCleaner.data

interface IssueNode {
    val id: String

    val type: String

    val name: String

    val value: String

    val children: List<IssueNode>

    fun add(node: IssueNode)

    fun remove(node: IssueNode)

    fun clear()
}