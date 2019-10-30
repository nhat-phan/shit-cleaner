package net.ntworld.intellijCodeCleaner.component.duplications

import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.wm.ToolWindow
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.component.issue.AbstractIssueTab
import javax.swing.JPanel

open class DuplicationsTab(
    ideaProject: IdeaProject,
    toolWindow: ToolWindow,
    private val componentFactory: ComponentFactory
): AbstractIssueTab(ideaProject, toolWindow, componentFactory) {
    override val dividerKey: String = this::class.java.canonicalName

}
