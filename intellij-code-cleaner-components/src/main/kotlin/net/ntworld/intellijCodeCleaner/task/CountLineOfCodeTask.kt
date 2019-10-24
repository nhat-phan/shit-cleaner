package net.ntworld.intellijCodeCleaner.task

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vcs.changes.ChangeListManager
import com.intellij.openapi.vfs.VirtualFile
import net.ntworld.codeCleaner.codeClimate.SupportedLanguages
import net.ntworld.intellijCodeCleaner.Plugin
import java.io.BufferedReader
import java.io.InputStreamReader

class CountLineOfCodeTask(project: Project) : Task.Backgroundable(project, "Counting line of code...", false) {

    override fun run(indicator: ProgressIndicator) {
        val rootManager = ProjectRootManager.getInstance(project)
        val changeListManager = ChangeListManager.getInstance(project)
        val roots = rootManager.contentRoots

        roots.forEach { count(it, rootManager, changeListManager) }
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
                    println("${file.path}: ${countLines(file)} ${language.name}")
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
        fun start(plugin: Plugin, projectId: String, project: Project) {
            ProgressManager.getInstance().run(CountLineOfCodeTask(project))
        }
    }

}
