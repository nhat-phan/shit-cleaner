package net.ntworld.intellijCodeCleaner

import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.wm.ToolWindow
import net.ntworld.codeCleaner.structure.MaintainabilityRate
import net.ntworld.foundation.MemorizedInfrastructure
import net.ntworld.intellijCodeCleaner.component.annotation.AnnotationGutterDataFactory
import net.ntworld.intellijCodeCleaner.component.annotation.AnnotationManager
import net.ntworld.intellijCodeCleaner.component.annotation.DefaultAnnotationManager
import net.ntworld.intellijCodeCleaner.component.annotation.internal.AnnotationGutterDataFactoryImpl
import net.ntworld.intellijCodeCleaner.component.button.*
import net.ntworld.intellijCodeCleaner.component.codeSmells.CodeSmellsTab
import net.ntworld.intellijCodeCleaner.component.duplications.DuplicationsTab
import net.ntworld.intellijCodeCleaner.component.overview.OverviewTab
import net.ntworld.intellijCodeCleaner.component.toolbar.MainToolbar
import net.ntworld.intellijCodeCleaner.component.util.Icons

object DefaultComponentFactory : ComponentFactory {
    private val annotationManagers = mutableMapOf<IdeaProject, AnnotationManager>()
    private val infrastructure = MemorizedInfrastructure(CodeCleaner(this))

    override fun makeInfrastructure() = infrastructure

    override fun findDispatcher(projectId: String) = DefaultDispatcher.findInstance(projectId)

    override fun makeDispatcher(ideaProject: IdeaProject) = DefaultDispatcher.getInstance(ideaProject)

    override fun makeAnalyzeButton(): AnalyzeButton {
        return AnalyzeButton(this)
    }

    override fun makeAnnotationToggleButton(): AnnotationToggleButton {
        return AnnotationToggleButton(this)
    }

    override fun makeMaintainabilityFilterButton(rate: MaintainabilityRate): MaintainabilityFilterButton {
        return MaintainabilityFilterButton(
            this,
            rate,
            when (rate) {
                MaintainabilityRate.Good -> Icons.MaintainabilityFilterGood
                MaintainabilityRate.Moderate -> Icons.MaintainabilityFilterModerate
                MaintainabilityRate.Bad -> Icons.MaintainabilityFilterBad
            }
        )
    }

    override fun makeStopButton(): StopButton {
        return StopButton(this)
    }

    override fun makeMainToolbar(): MainToolbar {
        return MainToolbar(this)
    }

    override fun makeOverviewTab(ideaProject: IdeaProject, toolWindow: ToolWindow): OverviewTab {
        return OverviewTab(ideaProject, toolWindow, this)
    }

    override fun makeCodeSmellsTab(ideaProject: IdeaProject, toolWindow: ToolWindow): CodeSmellsTab {
        return CodeSmellsTab(ideaProject, toolWindow, this)
    }

    override fun makeDuplicationsTab(ideaProject: IdeaProject, toolWindow: ToolWindow): DuplicationsTab {
        return DuplicationsTab(ideaProject, toolWindow, this)
    }

    override fun makeAnnotationManager(ideaProject: IdeaProject): AnnotationManager {
        if (null === annotationManagers[ideaProject]) {
            annotationManagers[ideaProject] = DefaultAnnotationManager(
                ideaProject, makeDispatcher(ideaProject), makeAnnotationGutterDataFactory(ideaProject)
            )
        }
        return annotationManagers[ideaProject]!!
    }

    override fun makeAnnotationGutterDataFactory(ideaProject: IdeaProject): AnnotationGutterDataFactory {
        return AnnotationGutterDataFactoryImpl(makeDispatcher(ideaProject).store)
    }
}