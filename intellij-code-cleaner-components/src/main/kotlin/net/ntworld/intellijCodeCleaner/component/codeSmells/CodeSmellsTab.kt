package net.ntworld.intellijCodeCleaner.component.codeSmells

import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.wm.ToolWindow
import net.ntworld.intellijCodeCleaner.ComponentFactory
import javax.swing.JPanel

open class CodeSmellsTab(
    private val ideaProject: IdeaProject,
    private val toolWindow: ToolWindow,
    private val componentFactory: ComponentFactory
) {
    fun createPanel(): JPanel {
        return JPanel()
    }
}