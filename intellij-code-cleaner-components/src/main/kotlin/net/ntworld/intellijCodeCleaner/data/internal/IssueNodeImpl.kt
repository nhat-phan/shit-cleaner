package net.ntworld.intellijCodeCleaner.data.internal

import net.ntworld.intellijCodeCleaner.data.IssueNode

internal class IssueNodeImpl(
    override val type: String,
    override var name: String = "",
    override var value: String = "",
    override var id: String = ""
) : IssueNode {
    override val children: MutableList<IssueNode> = mutableListOf()

    override infix fun add(node: IssueNode) {
        children.add(node)
    }

    override infix fun remove(node: IssueNode) {
        children.remove(node)
    }

    override fun clear() {
        children.clear()
    }
}