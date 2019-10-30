package net.ntworld.intellijCodeCleaner

import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.wm.ToolWindow
import net.ntworld.codeCleaner.structure.MaintainabilityRate
import net.ntworld.intellijCodeCleaner.component.button.*
import net.ntworld.intellijCodeCleaner.component.codeSmells.CodeSmellsTab
import net.ntworld.intellijCodeCleaner.component.duplications.DuplicationsTab
import net.ntworld.intellijCodeCleaner.component.overview.OverviewTab
import net.ntworld.intellijCodeCleaner.component.toolbar.MainToolbar
import net.ntworld.intellijCodeCleaner.component.util.Icons

object DefaultComponentFactory : ComponentFactory {

    override fun makeInfrastructure() = CodeCleaner()

    override fun makeDispatcher() = DefaultDispatcher

    override fun makeAnalyzeButton(): AnalyzeButton {
        return AnalyzeButton(makeDispatcher())
    }

    override fun makeAnnotationToggleButton(): AnnotationToggleButton {
        return AnnotationToggleButton(makeDispatcher())
    }

    override fun makeMaintainabilityFilterButton(rate: MaintainabilityRate): MaintainabilityFilterButton {
        return MaintainabilityFilterButton(
            makeDispatcher(),
            rate,
            when (rate) {
                MaintainabilityRate.Good -> Icons.MaintainabilityFilterGood
                MaintainabilityRate.Moderate -> Icons.MaintainabilityFilterModerate
                MaintainabilityRate.Bad -> Icons.MaintainabilityFilterBad
            }
        )
    }

    override fun makeStopButton(): StopButton {
        return StopButton(makeDispatcher())
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

}