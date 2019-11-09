package net.ntworld.intellijCodeCleaner.component.issue.node

import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.ui.SimpleTextAttributes
import net.ntworld.intellijCodeCleaner.ISSUE_NODE_TEXT_LINE
import net.ntworld.intellijCodeCleaner.data.IssueNodeData

class MainIssueNode(
    ideaProject: IdeaProject,
    val data: IssueNodeData
) : NodeBase(ideaProject, data) {

    override fun createPresentation(): PresentationData {
        val presentation = PresentationData()
        presentation.setIcon(AllIcons.Nodes.ErrorIntroduction)
        presentation.addText(data.text[ISSUE_NODE_TEXT_LINE], SimpleTextAttributes.GRAYED_ATTRIBUTES)
        presentation.addText(": ", SimpleTextAttributes.GRAYED_ATTRIBUTES)
        presentation.addText(data.name, SimpleTextAttributes.REGULAR_ATTRIBUTES)

        return presentation
    }

}

