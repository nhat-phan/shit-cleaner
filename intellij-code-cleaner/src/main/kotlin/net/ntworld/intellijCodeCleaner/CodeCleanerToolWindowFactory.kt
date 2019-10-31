package net.ntworld.intellijCodeCleaner

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.content.ContentFactory
import net.ntworld.intellijCodeCleaner.component.ToolWindowFactoryBase

class CodeCleanerToolWindowFactory : ToolWindowFactoryBase() {
    override val componentFactory = DefaultComponentFactory

    override fun createToolWindowContent(ideaProject: Project, toolWindow: ToolWindow) {
        super.createToolWindowContent(ideaProject, toolWindow)
        toolWindow.contentManager.addContent(
            ContentFactory.SERVICE.getInstance().createContent(
                AboutProVersion().createComponent(), "About", false
            )
        )
    }
}