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
    data class LineInfo(
        val start: Boolean,
        val original: Boolean,
        val issueIds: List<String>,
        val offset: Int
    )

    protected val issues : Map<String, AnnotationData.Item> by lazy {
        val result = mutableMapOf<String, AnnotationData.Item>()
        data.issues.forEach { result[it.id] = it }
        result
    }

    protected val lines : Map<Int, LineInfo> by lazy {
        val result = mutableMapOf<Int, LineInfo>()
        data.issues.forEach {
            for (i in it.lines.begin..it.lines.end) {
                val current = result[i]
                if (null === current) {
                    result[i] = LineInfo(
                        start = i == it.lines.begin,
                        original = true,
                        issueIds = listOf(it.id),
                        offset = 1
                    )
                } else {
                    result[i] = current.copy(
                        start = i == it.lines.begin,
                        original = true,
                        issueIds = current.issueIds + listOf(it.id),
                        offset = current.offset + 1
                    )
                }
            }
        }
        result
    }

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

        val data = lines[line]
        if (null === data) {
            return null
        }
        if (data.start) {
            // return "\uD83D\uDCA9"
            return " "
        }
        return " "
    }

    override fun getToolTip(line: Int, editor: Editor?): String? {
        return null
//        if (!this.plugin.store.mainToolbar.openingAnnotations) {
//            return null
//        }
//        val issues = issueByLines[line]
//        if (null === issues || issues.isEmpty()) {
//            return ""
//        }
//        return issues.map { it.description }.joinToString("\n\n")
    }

    override fun getStyle(line: Int, editor: Editor?): EditorFontType {
        return EditorFontType.PLAIN
    }

    override fun getBgColor(line: Int, editor: Editor?): Color? {
        val data = lines[line]
        if (null === data) {
            return null
        }
        if (data.start) {
            return null
        }
        return Color.RED
    }

    override fun gutterClosed() {
    }

}