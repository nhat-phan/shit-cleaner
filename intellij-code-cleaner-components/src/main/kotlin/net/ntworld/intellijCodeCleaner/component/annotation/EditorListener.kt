package net.ntworld.intellijCodeCleaner.component.annotation

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class EditorListener(
    private val ideaProject: Project,
    private val annotationManager: AnnotationManager
) : FileEditorManagerListener {
    init {
        ideaProject.messageBus.connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, this)
    }

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        annotationManager.attach(source.selectedEditor)
    }

    override fun selectionChanged(event: FileEditorManagerEvent) {
        annotationManager.attach(event.newEditor)
    }
}