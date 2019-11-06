package net.ntworld.intellijCodeCleaner.data

import net.ntworld.intellijCodeCleaner.ISSUE_NODE_TYPE_DIRECTORY
import net.ntworld.intellijCodeCleaner.ISSUE_NODE_TYPE_FILE
import net.ntworld.intellijCodeCleaner.ISSUE_NODE_TYPE_ROOT
import net.ntworld.intellijCodeCleaner.data.internal.IssueNodeImpl
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class IssueNodeBuilderTest {
    @Test
    fun `test rootNote should be created automatically`() {
        val builder = IssueNodeBuilder()
        assertEquals(ISSUE_NODE_TYPE_ROOT, builder.rootNode.type)
        assertEquals("", builder.rootNode.name)
    }

    @Test
    fun `test rootNote can be passed outside`() {
        val root = IssueNodeImpl(
            type = ISSUE_NODE_TYPE_ROOT,
            name = "~/test"
        )
        val builder = IssueNodeBuilder(root)
        assertSame(root, builder.rootNode)
    }

    @Test
    fun `test appendPathComponentsToTree can create leap in Tree`() {
        val components = listOf("first", "second", "third", "File.kt")
        val builder = IssueNodeBuilder()
        builder.appendPathComponentsToTree(components)

        expectDirectoryNode(findByPath(builder, 0), "first")
        expectDirectoryNode(findByPath(builder, 0, 0), "second")
        expectDirectoryNode(findByPath(builder, 0, 0, 0), "third")
        expectFileNode(findByPath(builder, 0, 0, 0, 0), "File.kt")
    }

    @Test
    fun `test appendPathComponentsToTree can update leap in Tree`() {
        val componentsOne = listOf("first", "second", "third", "File.kt")
        val componentsTwo = listOf("first", "new", "Update.kt")
        val builder = IssueNodeBuilder()
        builder.appendPathComponentsToTree(componentsOne)
        builder.appendPathComponentsToTree(componentsTwo)

        expectDirectoryNode(findByPath(builder, 0), "first")

        expectDirectoryNode(findByPath(builder, 0, 0), "second")
        expectDirectoryNode(findByPath(builder, 0, 1), "new")

        expectDirectoryNode(findByPath(builder, 0, 0, 0), "third")
        expectFileNode(findByPath(builder, 0, 1, 0), "Update.kt")

        expectFileNode(findByPath(builder, 0, 0, 0, 0), "File.kt")
    }

    @Test
    fun `test appendPathComponentsToTree can create directory and file as the same name`() {
        val componentsOne = listOf("first", "second", "File.kt")
        val componentsTwo = listOf("first", "second")
        val builder = IssueNodeBuilder()
        builder.appendPathComponentsToTree(componentsOne)
        builder.appendPathComponentsToTree(componentsTwo)

        expectDirectoryNode(findByPath(builder, 0), "first")

        expectDirectoryNode(findByPath(builder, 0, 0), "second")
        expectFileNode(findByPath(builder, 0, 1), "second")

        expectFileNode(findByPath(builder, 0, 0, 0), "File.kt")
    }

    private fun expectDirectoryNode(node: IssueNode, name: String) {
        assertEquals(ISSUE_NODE_TYPE_DIRECTORY, node.type)
        assertEquals(name, node.name)
    }

    private fun expectFileNode(node: IssueNode, name: String) {
        assertEquals(ISSUE_NODE_TYPE_FILE, node.type)
        assertEquals(name, node.name)
    }

    private fun findByPath(builder: IssueNodeBuilder, vararg index: Int): IssueNode {
        var node = builder.rootNode
        val indexes = index.toList()
        for (i in 0 until indexes.size) {
            node = node.children[indexes[i]]
        }
        return node
    }
}