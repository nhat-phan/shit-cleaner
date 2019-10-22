package net.ntworld.intellijCodeCleaner.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import net.ntworld.codeCleaner.command.CreateProjectCommand
import net.ntworld.codeCleaner.make
import net.ntworld.codeCleaner.query.FindProjectByIdQuery
import net.ntworld.codeCleaner.request.CheckLicenseRequest
import net.ntworld.foundation.Infrastructure
import net.ntworld.intellijCodeCleaner.IntellijCodeCleaner
import net.ntworld.intellijCodeCleaner.IntellijCodeCleanerPlugin
import net.ntworld.intellijCodeCleaner.ui.overview.OverviewTab
import java.lang.Exception

class CodeCleanerToolWindowFactory : ToolWindowFactory {
    private fun init(project: Project) {
        val infrastructure = IntellijCodeCleaner()
        infrastructure {
            val response = serviceBus().process(CheckLicenseRequest.make())
            if (response.getResponse().isFailure) {
                throw Exception("No license!")
            }

            val license = response.getResponse().license
            if (!license.multiProjects) {
                initSingleProjectPlugin(infrastructure, project.basePath!!)
            }
        }
    }

    private fun initSingleProjectPlugin(infrastructure: Infrastructure, path: String) = infrastructure {
        val id = infrastructure.idGeneratorOf().generate()
        commandBus().process(
            CreateProjectCommand.make(
                id = id,
                name = "default",
                path = path
            )
        )
        IntellijCodeCleanerPlugin.registerProject(
            queryBus().process(FindProjectByIdQuery.make(id))
        )
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        init(project)

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