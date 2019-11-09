package net.ntworld.intellijCodeCleaner.component.issue

import com.intellij.find.FindModel
import com.intellij.find.impl.FindInProjectUtil
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.OnePixelSplitter
import com.intellij.ui.ScrollPaneFactory
import com.intellij.usages.impl.UsagePreviewPanel
import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.AppStore
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.component.issue.node.FileNode
import net.ntworld.intellijCodeCleaner.component.issue.node.MainIssueNode
import net.ntworld.intellijCodeCleaner.component.issue.node.RelatedIssueNode
import net.ntworld.intellijCodeCleaner.util.IdeaProjectUtil
import javax.swing.JPanel
import javax.swing.event.TreeSelectionEvent
import javax.swing.event.TreeSelectionListener
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreePath

abstract class AbstractIssueTab(
    private val ideaProject: Project,
    private val toolWindow: ToolWindow,
    private val componentFactory: ComponentFactory
) : TreeSelectionListener {
    protected abstract val dividerKey: String

    protected abstract fun getIssues(store: AppStore): Collection<Issue>

    protected abstract fun findIssue(store: AppStore, id: String): Issue?

    protected val store = componentFactory.makeDispatcher().store

    private val splitter by lazy {
        OnePixelSplitter(false, dividerKey, 0.5f)
    }
    protected open val issueTree = IssueTree(ideaProject)

    fun createPanel(): JPanel {
        val usagePreviewPanel = UsagePreviewPanel(
            ideaProject,
            FindInProjectUtil.setupViewPresentation(false, FindModel())
        )
        store.onChange("project", this::updateComponents)

        issueTree.addTreeSelectionListener(this)

        splitter.firstComponent = ScrollPaneFactory.createScrollPane(issueTree.component)
        splitter.secondComponent = ScrollPaneFactory.createScrollPane(usagePreviewPanel)

        return splitter
    }

    override fun valueChanged(e: TreeSelectionEvent?) {
        if (null === e) {
            return
        }

        val path = e.path
        when (val node = (path.lastPathComponent as DefaultMutableTreeNode).userObject) {
            is FileNode -> onFileNodeSelected(node)
            is MainIssueNode -> onMainIssueNodeSelected(path, node)
            is RelatedIssueNode -> onRelatedIssueNodeSelected(path, node)
        }
    }

    protected open fun onFileNodeSelected(node: FileNode) {
        val file = IdeaProjectUtil.findVirtualFileByPath(node.data.value)
        if (null !== file) {
            FileEditorManager.getInstance(ideaProject).openFile(file, false)
        }
    }

    protected open fun onMainIssueNodeSelected(path: TreePath, node: MainIssueNode) {
        val fileNode = (path.parentPath.lastPathComponent as DefaultMutableTreeNode).userObject as? FileNode ?: return
        val file = IdeaProjectUtil.findVirtualFileByPath(fileNode.data.value)
        val issue = findIssue(store, node.data.issueId)
        if (null !== file && null !== issue) {
            val descriptor = OpenFileDescriptor(ideaProject, file, issue.lines.begin - 1, 0, true)
            FileEditorManager.getInstance(ideaProject).openEditor(descriptor, false)
        }
    }

    protected open fun onRelatedIssueNodeSelected(path: TreePath, node: RelatedIssueNode) {
        println(node.data)
    }

    protected open fun updateComponents() {
        if (!store.project.hasResult) {
            issueTree.updateBy(listOf(), store.project.id, store.project.basePath)
        } else {
            issueTree.updateBy(getIssues(store), store.project.id, store.project.basePath)
        }
    }
}