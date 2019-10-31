package net.ntworld.intellijCodeCleaner.component.overview.node

import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.util.treeView.PresentableNodeDescriptor
import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.ui.AnimatedIcon
import com.intellij.ui.SimpleTextAttributes
import net.ntworld.intellijCodeCleaner.state.ProjectState
import net.ntworld.intellijCodeCleaner.toDateTimeString

class ProgressRootNode(
    ideaProject: IdeaProject,
    private val state: ProjectState
) : PresentableNodeDescriptor<Unit>(ideaProject, null) {

    override fun createPresentation(): PresentationData {
        val presentation = PresentationData()
        presentation.addText("Code Cleaner", SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES)
        if (null !== state.time) {
            if (state.hasResult) {
                presentation.addText(
                    " analyzed on ${state.time.toDateTimeString(" at ")}",
                    SimpleTextAttributes.GRAYED_ITALIC_ATTRIBUTES
                )
            } else {
                presentation.addText(
                    " is running on ${state.time.toDateTimeString(" at ")}",
                    SimpleTextAttributes.GRAYED_ITALIC_ATTRIBUTES
                )
            }
        }

        if (state.analyzing || state.counting) {
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