package net.ntworld.intellijCodeCleaner.component.annotation

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.TextAnnotationGutterProvider
import com.intellij.openapi.editor.colors.ColorKey
import com.intellij.openapi.editor.colors.EditorFontType
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.vcs.ProjectLevelVcsManager
import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.vcs.changes.ChangeListManager
import com.intellij.openapi.vcs.impl.LineStatusTrackerManager
import com.intellij.openapi.vfs.VirtualFile
import net.ntworld.intellijCodeCleaner.Plugin
import java.awt.Color

class AnnotationGutterProvider(
    private val plugin: Plugin
) : TextAnnotationGutterProvider {

    override fun getPopupActions(line: Int, editor: Editor?): MutableList<AnAction> {
        return mutableListOf()
    }

    override fun getColor(line: Int, editor: Editor?): ColorKey? {
        return null
    }

    override fun getLineText(line: Int, editor: Editor?): String? {
//        if (!this.plugin.store.mainToolbar.openingAnnotations) {
//            return null
//        }

        return "\uD83D\uDCA9"
    }

    override fun getToolTip(line: Int, editor: Editor?): String? {
        return ""
    }

    override fun getStyle(line: Int, editor: Editor?): EditorFontType {
        return EditorFontType.PLAIN
    }

    override fun getBgColor(line: Int, editor: Editor?): Color? {
        return null
    }

    override fun gutterClosed() {
    }

}