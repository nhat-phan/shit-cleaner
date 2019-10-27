package net.ntworld.intellijCodeCleaner.task

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vcs.changes.ChangeListManager
import com.intellij.openapi.vfs.VirtualFile
import net.ntworld.codeCleaner.codeClimate.SupportedLanguages
import net.ntworld.codeCleaner.statistic.CodeStatistic
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.CodeStatisticFinishedAction
import net.ntworld.intellijCodeCleaner.action.CodeStatisticStartedAction
import net.ntworld.intellijCodeCleaner.util.IdeaProjectUtil
import java.io.BufferedReader
import java.io.InputStreamReader

class CountLineOfCodeTask private constructor(
    private val plugin: Plugin,
    private val projectId: String,
    ideaProject: IdeaProject
) : Task.Backgroundable(ideaProject, "Counting line of code...", false) {
    private val statistic = CodeStatistic()

    override fun run(indicator: ProgressIndicator) {
        plugin dispatch CodeStatisticStartedAction(projectId)

        val rootManager = ProjectRootManager.getInstance(project)
        val changeListManager = ChangeListManager.getInstance(project)
        IdeaProjectUtil.getAvailableContentRoots(project).forEach {
            count(it, rootManager, changeListManager)
        }

        Thread.sleep(2000)
        plugin dispatch CodeStatisticFinishedAction(projectId, statistic.buildData())
    }

    private fun skip(file: VirtualFile, rootManager: ProjectRootManager, changeList: ChangeListManager): Boolean {
        return rootManager.fileIndex.isExcluded(file) || changeList.isVcsIgnoredFile(file)
    }

    private fun count(root: VirtualFile, rootManager: ProjectRootManager, changeListManager: ChangeListManager) {
        if (skip(root, rootManager, changeListManager)) {
            return
        }

        for (file in root.children) {
            if (file.isDirectory) {
                count(file, rootManager, changeListManager)
                continue
            }

            val extension = file.extension
            if (null !== extension && !skip(file, rootManager, changeListManager)) {
                val language = SupportedLanguages.findLanguageByExtension(extension)
                if (null !== language) {
                    statistic.collect(file.path, countLines(file), language)
                }
            }
        }
    }

    private fun countLines(file: VirtualFile): Int {
        val reader = BufferedReader(InputStreamReader(file.inputStream, file.charset))
        reader.use {
            var count = 1;
            val iterator = reader.lineSequence().iterator()
            while (iterator.hasNext()) {
                count++
                iterator.next()
            }
            return count
        }
    }

    companion object {
        fun start(plugin: Plugin, projectId: String, ideaProject: IdeaProject) {
            ProgressManager.getInstance().run(CountLineOfCodeTask(plugin, projectId, ideaProject))
        }
    }

}
