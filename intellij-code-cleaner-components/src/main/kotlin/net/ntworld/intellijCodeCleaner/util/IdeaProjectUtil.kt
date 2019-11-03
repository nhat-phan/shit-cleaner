package net.ntworld.intellijCodeCleaner.util

import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vcs.changes.ChangeListManager
import com.intellij.openapi.vfs.VirtualFile
import net.ntworld.intellijCodeCleaner.data.ContentRootInfo
import java.io.File

object IdeaProjectUtil {

    fun getContentRootInfos(ideaProject: IdeaProject): List<ContentRootInfo> {
        val roots = getAvailableContentRoots(ideaProject)
        val basePath = ideaProject.basePath!!

        return roots
            .map {

                val path = it.path
                val displayPath = path.replace(basePath, "~")
                val relativePath = path.replace(basePath, "")
                ContentRootInfo(
                    name = displayPath,
                    path = if (relativePath.startsWith(File.separator)) relativePath.substring(1) else relativePath,
                    displayPath = displayPath,
                    virtualFile = it
                )
            }
            .sortedByDescending { it.virtualFile.path }
    }

    fun getAvailableContentRoots(ideaProject: IdeaProject): List<VirtualFile> {
        val rootManager = ProjectRootManager.getInstance(ideaProject)
        val changeListManager = ChangeListManager.getInstance(ideaProject)

        return rootManager.contentSourceRoots.filter {
            !rootManager.fileIndex.isExcluded(it) && !changeListManager.isVcsIgnoredFile(it)
        }
    }

}