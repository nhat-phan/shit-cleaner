package net.ntworld.intellijCodeCleaner.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import net.ntworld.intellijCodeCleaner.ui.overview.OverviewTab

class CodeCleanerToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = CodeCleanerToolWindow(toolWindow)
        val codeSmellsTabContent = CodeSmellsTabContent(project)

        val contentFactory = ContentFactory.SERVICE.getInstance()
        // Content of Overview tab

        val content2 = contentFactory.createContent(
            MainToolbar.apply(codeSmellsTabContent.getContent()!!), "Code Smells", false
        )
        val content3 = contentFactory.createContent(
            MainToolbar.apply(myToolWindow.getContent()!!), "Duplications", false
        )

        toolWindow.contentManager.addContent(OverviewTab(project).getContent())
        toolWindow.contentManager.addContent(content2)
        toolWindow.contentManager.addContent(content3)

//        if (toolWindow is ToolWindowEx) {
//            toolWindow.setAdditionalGearActions(group)
//        }
    }
}