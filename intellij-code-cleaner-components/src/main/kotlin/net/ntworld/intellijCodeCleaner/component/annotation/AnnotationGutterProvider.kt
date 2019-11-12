package net.ntworld.intellijCodeCleaner.component.annotation

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.TextAnnotationGutterProvider
import com.intellij.openapi.editor.colors.ColorKey
import com.intellij.openapi.editor.colors.EditorFontType
import net.ntworld.intellijCodeCleaner.AppStore
import net.ntworld.redux.Dispatcher
import java.awt.Color

open class AnnotationGutterProvider(
    private val dispatcher: Dispatcher<AppStore>,
    private val data: AnnotationGutterData,
    private val opacity: Int
) : TextAnnotationGutterProvider {
    override fun getPopupActions(line: Int, editor: Editor?): MutableList<AnAction> {
        return mutableListOf()
    }

    override fun getColor(line: Int, editor: Editor?): ColorKey? {
        return null
    }

    override fun getLineText(line: Int, editor: Editor?): String? {
        if (!this.dispatcher.store.mainToolbar.openingAnnotations) {
            return null
        }

        val lineData = data.lines[line+1]
        if (null === lineData) {
            return null
        }
        return " "
    }

    override fun getToolTip(line: Int, editor: Editor?): String? {
        val lineData = data.lines[line+1]
        if (null === lineData) {
            return null
        }
        return data.issues[lineData.issueId]!!.description
    }

    override fun getStyle(line: Int, editor: Editor?): EditorFontType {
        return EditorFontType.PLAIN
    }

    override fun getBgColor(line: Int, editor: Editor?): Color? {
        val lineData = data.lines[line+1]
        if (null === lineData) {
            return null
        }
        return Color(135, 92, 54, opacity)
    }

    override fun gutterClosed() {
    }


}