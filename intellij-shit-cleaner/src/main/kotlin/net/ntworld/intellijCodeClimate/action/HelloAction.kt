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
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import org.apache.commons.exec.ExecuteWatchdog
import org.apache.commons.exec.PumpStreamHandler
import java.awt.Color
import java.io.*
import java.nio.file.Paths


class AnalyzeRunner(private val project: Project) : Runnable {
    override fun run() {
        val codeClimate = IntellijCodeClimate()
        codeClimate {
            val analyzeCommand = AnalyzeCommand.make(
                basePath = project.basePath!!,
                path = null,
                engine = null
            )

            commandBus().process(analyzeCommand)
        }
    }
}

class AnalyzeTask(
    private val currentProject: Project
) : Task.Backgroundable(currentProject, "Code climate analyzing...", true) {
    var process: Process? = null

    override fun onCancel() {
        super.onCancel()
        process!!.destroy()
    }

    override fun run(indicator: ProgressIndicator) {
//        val codeClimate = IntellijCodeClimate()
//        codeClimate {
//            val analyzeCommand = AnalyzeCommand.make(
//                basePath = project.basePath!!,
//                path = null,
//                engine = null
//            )
//
//            commandBus().process(analyzeCommand)
//        }

//        val builder = ProcessBuilder()
//        builder.redirectErrorStream(true)
//        builder.directory(
//            Paths.get(currentProject.basePath!!).toFile()
//        )
//
//        builder.command("codeclimate analyze -f json")
//        process = builder.start()
//        println("Started")
//
//        val input = BufferedReader(InputStreamReader(process!!.inputStream))
//        val inputIterator = input.lineSequence().iterator()
//        while (inputIterator.hasNext()) {
//            println(inputIterator.next())
//        }
//        input.close()
//
//        val code = process!!.waitFor()
//
//        println("OK $code")

        println("Start")
        runCommand("bash", arrayOf("-c", "codeclimate analyze -f json"))
    }

    private fun runCommand(cmd: String, params: Array<String>): String {
        val commandLine = CommandLine(cmd)
        commandLine.addArguments(params, false)
        val stdout = ByteArrayOutputStream()
        val stderr = ByteArrayOutputStream()
        val pumpStreamHandler = PumpStreamHandler(stdout, stderr)
        val watchdog = ExecuteWatchdog(60 * 1000)
        val executor = DefaultExecutor()
        executor.workingDirectory = Paths.get(currentProject.basePath!!).toFile()
        executor.streamHandler = pumpStreamHandler
        executor.watchdog = watchdog

        try {
            val retCode = executor.execute(commandLine)
            println(
                "Executed '" + cmd + "'\n"
                    + "returnCode: " + retCode + "\n"
                    + "stdout:\n" + stdout.toString() + "\n"
                    + "stderr:\n" + stderr.toString()
            )

            return if (retCode == 0) {
                stdout.toString()
            } else {
                // throw NonZeroExitStatusReturnedException(commandLine.toString(), retCode)
                throw Exception(commandLine.toString())
            }

        } catch (e: IOException) {
            throw RuntimeException("Could not run command $commandLine", e)
        }
    }
}


class HelloTextAnnotationGutterProvider: TextAnnotationGutterProvider {
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

class HelloLineMaker() {

    fun highlight(project: Project) {
        val editor = FileEditorManager.getInstance(project).selectedTextEditor!!
        editor.gutter.registerTextAnnotation(
            HelloTextAnnotationGutterProvider()
        )

//        for (editor in editors) {
//            editor.mak
//        }
        // editor.markupModel.addLineHighlighter()
        // editor.gutter.
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