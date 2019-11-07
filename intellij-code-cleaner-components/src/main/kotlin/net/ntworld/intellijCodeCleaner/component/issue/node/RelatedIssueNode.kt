package net.ntworld.intellijCodeCleaner.component.issue.node

import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.ui.SimpleTextAttributes
import net.ntworld.intellijCodeCleaner.data.IssueNodeData

class RelatedIssueNode(
    ideaProject: IdeaProject,
    val data: IssueNodeData
) : NodeBase(ideaProject, data) {

    override fun createPresentation(): PresentationData {
        val presentation = PresentationData()
        presentation.setIcon(AllIcons.Nodes.WarningIntroduction)
        presentation.addText(data.name, SimpleTextAttributes.REGULAR_ATTRIBUTES)
        presentation.addText(" ", SimpleTextAttributes.REGULAR_ATTRIBUTES)
        presentation.addText(data.value, SimpleTextAttributes.GRAYED_ATTRIBUTES)

        return presentation
    }

}

