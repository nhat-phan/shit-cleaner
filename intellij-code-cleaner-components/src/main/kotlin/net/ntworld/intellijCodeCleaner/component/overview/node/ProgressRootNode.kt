package net.ntworld.intellijCodeCleaner.component.overview.node

import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.util.treeView.PresentableNodeDescriptor
import com.intellij.openapi.project.Project
import com.intellij.ui.AnimatedIcon
import com.intellij.ui.SimpleTextAttributes
import net.ntworld.intellijCodeCleaner.state.ProjectState

class ProgressRootNode(
    project: Project,
    private val state: ProjectState
) : PresentableNodeDescriptor<Unit>(project, null) {
    override fun createPresentation(): PresentationData {
        val presentation = PresentationData()
        presentation.addText("Code Cleaner", SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES)

        if (state.analyzing) {
            presentation.setIcon(AnimatedIcon.FS())
        } else {
            presentation.setIcon(AllIcons.RunConfigurations.TestPassed)
        }
        return presentation
    }

    override fun update(presentation: PresentationData) {
    }

    override fun getElement(): Unit {
    }
}