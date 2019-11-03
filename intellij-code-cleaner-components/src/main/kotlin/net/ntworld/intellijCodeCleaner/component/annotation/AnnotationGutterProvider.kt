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

open class AnnotationGutterProvider(
    private val plugin: Plugin,
    private val data: AnnotationData
) : TextAnnotationGutterProvider {
    protected val issueByLines : Map<Int, List<AnnotationData.Item>> by lazy {
        val result = mutableMapOf<Int, MutableList<AnnotationData.Item>>()
        data.issues.forEach {
            for (i in it.lines.begin..it.lines.end) {
                if (null === result[i]) {
                    result[i] = mutableListOf(it)
                } else {
                    result[i]!!.add(it)
                }
            }
            for (location in it.locations) {
                if (location.path != it.path) {
                    continue
                }

                for (i in location.lines.begin..location.lines.end) {
                    if (null === result[i]) {
                        result[i] = mutableListOf(it)
                    } else {
                        result[i]!!.add(it)
                    }
                }
            }

        }
        result
    }


    override fun getPopupActions(line: Int, editor: Editor?): MutableList<AnAction> {
        return mutableListOf()
    }

    override fun getColor(line: Int, editor: Editor?): ColorKey? {
        return null
    }

    override fun getLineText(line: Int, editor: Editor?): String? {
        if (!this.plugin.store.mainToolbar.openingAnnotations) {
            return null
        }
        val issues = issueByLines[line]
        if (null === issues || issues.isEmpty()) {
            return null
        }
        return "\uD83D\uDCA9"
    }

    override fun getToolTip(line: Int, editor: Editor?): String? {
        if (!this.plugin.store.mainToolbar.openingAnnotations) {
            return null
        }
        val issues = issueByLines[line]
        if (null === issues || issues.isEmpty()) {
            return ""
        }
        return issues.map { it.description }.joinToString("\n\n")
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