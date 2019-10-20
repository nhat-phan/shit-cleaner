package net.ntworld.intellijCodeCleaner.action

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import net.ntworld.intellijCodeCleaner.util.Icons

object ShowAnnotationAction : ToggleAction(null, null, Icons.ShowAnnotation) {

    override fun isSelected(e: AnActionEvent): Boolean {
        return false
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
    }

}