package net.ntworld.intellijCodeCleaner.action

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import javax.swing.Icon

open class FilterActionBase(description: String?, icon: Icon?) : ToggleAction(null, description, icon) {

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