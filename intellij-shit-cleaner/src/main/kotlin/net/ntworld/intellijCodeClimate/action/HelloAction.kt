package net.ntworld.intellijCodeClimate.action

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.TextAnnotationGutterProvider
import com.intellij.openapi.editor.colors.ColorKey
import com.intellij.openapi.editor.colors.EditorFontType
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import net.ntworld.codeClimate.command.AnalyzeCommand
import net.ntworld.codeClimate.make
import net.ntworld.intellijCodeClimate.IntellijCodeClimate
import java.awt.Color

class AnalyzeTask(
    private val currentProject: Project
) : Task.Backgroundable(currentProject, "Code climate analyzing...", true) {
    override fun run(indicator: ProgressIndicator) {
        println("Start")
        val codeClimate = IntellijCodeClimate()
        codeClimate {
            val analyzeCommand = AnalyzeCommand.make(
                basePath = project.basePath!!,
                path = null
            )

            commandBus().process(analyzeCommand)
        }
    }
}

class HelloAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        if (null !== project) {
            ProgressManager.getInstance().run(AnalyzeTask(project))
        }
    }

}



class HelloTextAnnotationGutterProvider : TextAnnotationGutterProvider {
    override fun getColor(line: Int, editor: Editor?): ColorKey? {
        TODO()
    }

    override fun getLineText(line: Int, editor: Editor?): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getToolTip(line: Int, editor: Editor?): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStyle(line: Int, editor: Editor?): EditorFontType {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBgColor(line: Int, editor: Editor?): Color? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun gutterClosed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPopupActions(line: Int, editor: Editor?): MutableList<AnAction> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
