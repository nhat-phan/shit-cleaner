package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import net.ntworld.codeCleaner.structure.MaintainabilityRate
import javax.swing.Icon

abstract class AbstractMaintainabilityFilterButton(icon: Icon?) : ToggleAction(null, null, icon) {
    abstract val rate: MaintainabilityRate

    override fun isSelected(e: AnActionEvent): Boolean {
        return false
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.isEnabled = false
    }

}