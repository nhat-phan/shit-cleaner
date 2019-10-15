package net.ntworld.intellijCodeClimate.action

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.TextAnnotationGutterProvider
import com.intellij.openapi.editor.colors.ColorKey
import com.intellij.openapi.editor.colors.EditorFontType
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.psi.PsiElement
import net.ntworld.codeClimate.command.AnalyzeCommand
import net.ntworld.codeClimate.make
import net.ntworld.intellijCodeClimate.IntellijCodeClimate
import net.ntworld.intellijCodeClimate.service.ProjectService
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
//            val editor = FileEditorManager.getInstance(project).selectedTextEditor
//            if (null !== editor) {
//                editor.gutter.registerTextAnnotation(HelloTextAnnotationGutterProvider())
//            }
        }
    }

}

class ApplicationServiceImpl : ProjectService {
    init {
        val projects = ProjectManager.getInstance().openProjects

    }
}

class HighlightAction: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR)!!
        editor.gutter.registerTextAnnotation(HelloTextAnnotationGutterProvider())
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        e.presentation.isEnabledAndVisible = null !== project && null !== editor
    }
}


class HelloTextAnnotationGutterProvider : TextAnnotationGutterProvider {
    override fun getColor(line: Int, editor: Editor?): ColorKey? {
        println("Line $line")
        return null
    }

    override fun getLineText(line: Int, editor: Editor?): String? {
        return " "
    }

    override fun getToolTip(line: Int, editor: Editor?): String? {
        return "Hello"
    }

    override fun getStyle(line: Int, editor: Editor?): EditorFontType {
        return EditorFontType.PLAIN
    }

    override fun getBgColor(line: Int, editor: Editor?): Color? {
        println("Line $line")
        return Color(255, 0, 0)
    }

    override fun gutterClosed() {
    }

    override fun getPopupActions(line: Int, editor: Editor?): MutableList<AnAction> {
        return mutableListOf()
    }
}
