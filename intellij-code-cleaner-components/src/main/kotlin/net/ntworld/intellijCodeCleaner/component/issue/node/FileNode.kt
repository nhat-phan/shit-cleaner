package net.ntworld.intellijCodeCleaner.component.issue.node

import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.openapi.util.Iconable
import com.intellij.psi.PsiFile
import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.ui.SimpleTextAttributes
import net.ntworld.intellijCodeCleaner.data.IssueNode

class FileNode(
    ideaProject: IdeaProject,
    private val data: IssueNode
) : NodeBase(ideaProject, data) {

    fun findIcon(file: PsiFile) {
        // TODO: find an icon
        file.getIcon(Iconable.ICON_FLAG_READ_STATUS)
    }

    override fun createPresentation(): PresentationData {
        val presentation = PresentationData()
        presentation.setIcon(AllIcons.Nodes.Field)
        presentation.addText(data.name, SimpleTextAttributes.REGULAR_ATTRIBUTES)

        return presentation
    }

}

