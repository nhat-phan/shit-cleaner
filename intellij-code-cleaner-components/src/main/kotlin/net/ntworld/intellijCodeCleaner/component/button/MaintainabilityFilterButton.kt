package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.codeCleaner.structure.MaintainabilityRate
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.ToggleMaintainabilityFilterAction
import javax.swing.Icon

open class MaintainabilityFilterButton(
    private val plugin: Plugin,
    val rate: MaintainabilityRate,
    icon: Icon?
) : ToggleAction(null, null, icon) {
    override fun isSelected(e: AnActionEvent): Boolean {
        return hasData() && hasAnyIssue() && isFiltering()
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        plugin dispatch ToggleMaintainabilityFilterAction(rate)
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.isEnabled = hasData() && hasAnyIssue()
    }

    protected open fun hasAnyIssue(): Boolean {
        return hasAnyIssueWithRate(plugin.store.project.codeSmells.values, rate) ||
            hasAnyIssueWithRate(plugin.store.project.duplications.values, rate)
    }

    protected open fun hasAnyIssueWithRate(collection: Collection<Issue>, rate: MaintainabilityRate): Boolean {
        for (issue in collection) {
            if (issue.fileRate == rate) {
                return true
            }
        }
        return false
    }

    protected open fun isFiltering(): Boolean {
        return when (rate) {
            MaintainabilityRate.Good -> plugin.store.mainToolbar.filteringByGoodIssues
            MaintainabilityRate.Moderate -> plugin.store.mainToolbar.filteringByModerateIssues
            MaintainabilityRate.Bad -> plugin.store.mainToolbar.filteringByBadIssues
        }
    }

    protected open fun hasData(): Boolean {
        return plugin.store.project.hasResult
    }
}