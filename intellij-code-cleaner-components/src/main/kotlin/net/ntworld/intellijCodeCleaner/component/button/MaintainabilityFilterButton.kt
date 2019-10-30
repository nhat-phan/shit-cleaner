package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
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
        return plugin.store.mainToolbar.hasData && hasAnyIssue() && isFiltering()
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        plugin dispatch ToggleMaintainabilityFilterAction(rate)
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.isEnabled = plugin.store.mainToolbar.hasData && hasAnyIssue()
    }

    protected open fun hasAnyIssue(): Boolean {
        return when (rate) {
            MaintainabilityRate.Good -> plugin.store.mainToolbar.hasGoodIssues
            MaintainabilityRate.Moderate -> plugin.store.mainToolbar.hasModerateIssues
            MaintainabilityRate.Bad -> plugin.store.mainToolbar.hasBadIssues
        }
    }

    protected open fun isFiltering(): Boolean {
        return when (rate) {
            MaintainabilityRate.Good -> plugin.store.mainToolbar.filteringByGoodIssues
            MaintainabilityRate.Moderate -> plugin.store.mainToolbar.filteringByModerateIssues
            MaintainabilityRate.Bad -> plugin.store.mainToolbar.filteringByBadIssues
        }
    }
}