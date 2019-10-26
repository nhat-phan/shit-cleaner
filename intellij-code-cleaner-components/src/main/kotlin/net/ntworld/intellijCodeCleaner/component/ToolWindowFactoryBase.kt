package net.ntworld.intellijCodeCleaner.component

import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import net.ntworld.codeCleaner.command.CreateProjectCommand
import net.ntworld.codeCleaner.make
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.action.ProjectInitializedAction
import net.ntworld.intellijCodeCleaner.component.toolbar.MainToolbar
import javax.swing.JPanel

abstract class ToolWindowFactoryBase : ToolWindowFactory {

    protected fun init(ideaProject: IdeaProject) {
        val infrastructure = componentFactory.makeInfrastructure()
        val id = infrastructure.idGeneratorOf().generate()
        infrastructure {
            commandBus().process(
                CreateProjectCommand.make(
                    id = id,
                    name = "default",
                    path = ideaProject.basePath!!
                )
            )
            componentFactory.makeDispatcher().dispatch(
                ProjectInitializedAction(id)
            )
        }
    }

    abstract val componentFactory: ComponentFactory

    override fun createToolWindowContent(ideaProject: IdeaProject, toolWindow: ToolWindow) {
        init(ideaProject)

        val mainToolbar = componentFactory.makeMainToolbar()
        val overviewTab = componentFactory.makeOverviewTab(ideaProject, toolWindow)
        val codeSmellsTab = componentFactory.makeCodeSmellsTab(ideaProject, toolWindow)
        val duplicationsTab = componentFactory.makeDuplicationsTab(ideaProject, toolWindow)

        toolWindow.contentManager.addContent(
            makeContent("Overview", mainToolbar, overviewTab.createPanel())
        )
        toolWindow.contentManager.addContent(
            makeContent("Code Smells", mainToolbar, codeSmellsTab.createPanel())
        )
        toolWindow.contentManager.addContent(
            makeContent("Duplications", mainToolbar, duplicationsTab.createPanel())
        )
    }

    private fun makeContent(displayName: String, mainToolbar: MainToolbar, panel: JPanel): Content {
        return ContentFactory.SERVICE.getInstance().createContent(
            mainToolbar.apply(panel), displayName, false
        )
    }

}