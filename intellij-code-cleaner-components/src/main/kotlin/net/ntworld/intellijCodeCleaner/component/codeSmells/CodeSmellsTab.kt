package net.ntworld.intellijCodeCleaner.component.codeSmells

import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.wm.ToolWindow
import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.AppStore
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.component.issue.AbstractIssueTab
import javax.swing.JPanel

open class CodeSmellsTab(
    ideaProject: IdeaProject,
    toolWindow: ToolWindow,
    private val componentFactory: ComponentFactory
): AbstractIssueTab(ideaProject, toolWindow, componentFactory) {
    override val dividerKey: String = this::class.java.canonicalName

    override fun getIssues(store: AppStore): Collection<Issue> {
        return store.project.codeSmells.values
    }
}