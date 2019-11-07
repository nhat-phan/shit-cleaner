package net.ntworld.intellijCodeCleaner.component.issue.node

import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.openapi.util.Iconable
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.ui.SimpleTextAttributes
import net.ntworld.intellijCodeCleaner.data.IssueNodeData
import net.ntworld.intellijCodeCleaner.util.IdeaProjectUtil
import javax.swing.Icon

class FileNode(
    private val ideaProject: IdeaProject,
    val data: IssueNodeData
) : NodeBase(ideaProject, data) {

    private fun findIcon(): Icon {
        val psiFile = IdeaProjectUtil.findPsiFile(ideaProject, data.value)
        if (null === psiFile) {
            return AllIcons.FileTypes.Any_type
        }
        return psiFile.getIcon(Iconable.ICON_FLAG_READ_STATUS)
    }

    override fun createPresentation(): PresentationData {
        val presentation = PresentationData()
        presentation.setIcon(findIcon())
        presentation.addText(data.name, SimpleTextAttributes.REGULAR_ATTRIBUTES)
        return presentation
    }

}

