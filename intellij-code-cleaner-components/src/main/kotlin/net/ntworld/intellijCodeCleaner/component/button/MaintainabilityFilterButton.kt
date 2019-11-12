package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.codeCleaner.structure.MaintainabilityRate
import net.ntworld.intellijCodeCleaner.*
import net.ntworld.intellijCodeCleaner.action.ToggleMaintainabilityFilterAction
import net.ntworld.redux.Dispatcher
import javax.swing.Icon

open class MaintainabilityFilterButton(
    private val componentFactory: ComponentFactory,
    val rate: MaintainabilityRate,
    icon: Icon?
) : ToggleAction(null, findTooltip(rate), icon) {
    override fun isSelected(e: AnActionEvent): Boolean {
        val project = e.project
        if (null === project) {
            return false
        }
        val dispatcher = componentFactory.makeDispatcher(project)
        return hasData(dispatcher) && hasAnyIssue(dispatcher) && isFiltering(dispatcher)
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        componentFactory.useDispatcherOf(e.project) {
            this dispatch ToggleMaintainabilityFilterAction(rate)
        }
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        val project = e.project
        if (null !== project) {
            val dispatcher = componentFactory.makeDispatcher(project)
            e.presentation.isEnabled = hasData(dispatcher) && hasAnyIssue(dispatcher)
        }

    }

    protected open fun hasAnyIssue(dispatcher: Dispatcher<AppStore>): Boolean {
        return hasAnyIssueWithRate(dispatcher.store.project.codeSmells.values, rate) ||
            hasAnyIssueWithRate(dispatcher.store.project.duplications.values, rate)
    }

    protected open fun hasAnyIssueWithRate(collection: Collection<Issue>, rate: MaintainabilityRate): Boolean {
        for (issue in collection) {
            if (issue.fileRate == rate) {
                return true
            }
        }
        return false
    }

    protected open fun isFiltering(dispatcher: Dispatcher<AppStore>): Boolean {
        return when (rate) {
            MaintainabilityRate.Good -> dispatcher.store.mainToolbar.filteringByGoodIssues
            MaintainabilityRate.Moderate -> dispatcher.store.mainToolbar.filteringByModerateIssues
            MaintainabilityRate.Bad -> dispatcher.store.mainToolbar.filteringByBadIssues
        }
    }

    protected open fun hasData(dispatcher: Dispatcher<AppStore>): Boolean {
        return dispatcher.store.project.hasResult
    }

    companion object {
        fun findTooltip(rate: MaintainabilityRate): String {
            return when (rate) {
                MaintainabilityRate.Good -> TOOLTIP_FILTER_GOOD_ISSUE
                MaintainabilityRate.Moderate -> TOOLTIP_FILTER_MODERATE_ISSUE
                MaintainabilityRate.Bad -> TOOLTIP_FILTER_BAD_ISSUE
            }
        }
    }
}