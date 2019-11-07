package net.ntworld.intellijCodeCleaner.util

import com.intellij.openapi.project.Project as IdeaProject
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vcs.changes.ChangeListManager
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import net.ntworld.intellijCodeCleaner.data.ContentRootInfo
import java.io.File

object IdeaProjectUtil {
    private val virtualFilesCache = mutableMapOf<String, VirtualFile>()
    private val psiFilesCache = mutableMapOf<String, PsiFile>()

    fun findVirtualFileByPath(path: String): VirtualFile? {
        if (virtualFilesCache.containsKey(path)) {
            return virtualFilesCache[path]
        }

        val file = LocalFileSystem.getInstance().findFileByPath(path)
        if (null !== file) {
            virtualFilesCache[path] = file
            return file
        }
        return null
    }

    fun findPsiFile(ideaProject: IdeaProject, path: String): PsiFile? {
        if (psiFilesCache.containsKey(path)) {
            return psiFilesCache[path]
        }

        // TODO: find another way to get rid of ideaProject if possible
        val file = findVirtualFileByPath(ideaProject.basePath + File.separator + path)
        if (null === file) {
            return null
        }
        val psiFile = PsiManager.getInstance(ideaProject).findFile(file)
        if (null !== psiFile) {
            psiFilesCache[path] = psiFile
        }
        return psiFile
    }

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