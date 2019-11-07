package net.ntworld.intellijCodeCleaner.component.issue.node

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.util.treeView.PresentableNodeDescriptor
import com.intellij.openapi.project.Project as IdeaProject
import net.ntworld.intellijCodeCleaner.data.IssueNodeData

open class NodeBase(
    ideaProject: IdeaProject,
    private val data: IssueNodeData
) : PresentableNodeDescriptor<IssueNodeData>(ideaProject, null) {
    override fun update(presentation: PresentationData) {}

    override fun getElement(): IssueNodeData = data
}