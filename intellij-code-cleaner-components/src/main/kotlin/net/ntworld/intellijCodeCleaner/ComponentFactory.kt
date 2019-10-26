package net.ntworld.intellijCodeCleaner

import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.wm.ToolWindow
import net.ntworld.codeCleaner.structure.MaintainabilityRate
import net.ntworld.foundation.Infrastructure
import net.ntworld.intellijCodeCleaner.component.button.AbstractMaintainabilityFilterButton
import net.ntworld.intellijCodeCleaner.component.button.AnalyzeButton
import net.ntworld.intellijCodeCleaner.component.button.AnnotationToggleButton
import net.ntworld.intellijCodeCleaner.component.button.StopButton
import net.ntworld.intellijCodeCleaner.component.codeSmells.CodeSmellsTab
import net.ntworld.intellijCodeCleaner.component.duplications.DuplicationsTab
import net.ntworld.intellijCodeCleaner.component.overview.OverviewTab
import net.ntworld.intellijCodeCleaner.component.toolbar.MainToolbar
import net.ntworld.redux.Dispatcher

interface ComponentFactory {

    fun makeInfrastructure(): Infrastructure

    fun makeDispatcher(): Dispatcher<out AppStore>

    fun makeAnalyzeButton(): AnalyzeButton

    fun makeAnnotationToggleButton(): AnnotationToggleButton

    fun makeMaintainabilityFilterButton(maintainabilityRate: MaintainabilityRate): AbstractMaintainabilityFilterButton

    fun makeStopButton(): StopButton

    fun makeMainToolbar(): MainToolbar

    fun makeOverviewTab(ideaProject: IdeaProject, toolWindow: ToolWindow): OverviewTab

    fun makeCodeSmellsTab(ideaProject: IdeaProject, toolWindow: ToolWindow): CodeSmellsTab

    fun makeDuplicationsTab(ideaProject: IdeaProject, toolWindow: ToolWindow): DuplicationsTab

}