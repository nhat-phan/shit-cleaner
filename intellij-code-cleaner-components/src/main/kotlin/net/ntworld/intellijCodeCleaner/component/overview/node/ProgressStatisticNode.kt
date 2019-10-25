package net.ntworld.intellijCodeCleaner.component.overview.node

import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.util.treeView.PresentableNodeDescriptor
import com.intellij.openapi.project.Project
import com.intellij.ui.AnimatedIcon
import com.intellij.ui.SimpleTextAttributes
import net.ntworld.intellijCodeCleaner.state.ProjectState

class ProgressStatisticNode(
    project: Project,
    private val state: ProjectState
) : PresentableNodeDescriptor<Unit>(project, null) {
    override fun createPresentation(): PresentationData {
        val presentation = PresentationData()

        if (state.counting) {
            presentation.addText("Collecting code statistic...", SimpleTextAttributes.REGULAR_ATTRIBUTES)
            presentation.setIcon(AnimatedIcon.FS())
        } else {
            presentation.addText("Collected code statistic", SimpleTextAttributes.REGULAR_ATTRIBUTES)
            presentation.setIcon(AllIcons.RunConfigurations.TestPassed)
        }
        return presentation
    }

    override fun update(presentation: PresentationData) {
    }

    override fun getElement(): Unit {
    }
}