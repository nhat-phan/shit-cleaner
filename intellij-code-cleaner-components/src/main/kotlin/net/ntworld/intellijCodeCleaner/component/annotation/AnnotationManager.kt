package net.ntworld.intellijCodeCleaner.component.annotation

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorKind
import com.intellij.openapi.editor.ex.EditorGutterComponentEx
import com.intellij.openapi.fileEditor.*
import com.intellij.openapi.vfs.VirtualFile
import net.ntworld.intellijCodeCleaner.AppStore
import net.ntworld.intellijCodeCleaner.Plugin
import com.intellij.openapi.project.Project as IdeaProject

open class AnnotationManager(
    private val ideaProject: IdeaProject,
    private val plugin: Plugin
) : FileEditorManagerListener {

    fun initialize() {
        ideaProject.messageBus.connect().subscribe(
            FileEditorManagerListener.FILE_EDITOR_MANAGER, this
        )
        val fileManager = FileEditorManager.getInstance(ideaProject)
        fileManager.selectedEditors.forEach {
            attachProvider(it)
        }
    }

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        attachProvider(source.selectedEditor)
    }

    override fun selectionChanged(event: FileEditorManagerEvent) {
        attachProvider(event.newEditor)
    }

    protected open fun attachProvider(editor: FileEditor?) {
        if (null === editor) {
            return
        }

        if (editor is TextEditor) {
            return this.attachProviderToEditor(editor.editor, editor.file)
        }
    }

    protected open fun attachProviderToEditor(editor: Editor, file: VirtualFile?) {
        if (editor.editorKind != EditorKind.MAIN_EDITOR && editor.editorKind != EditorKind.UNTYPED) {
            return
        }

        val gutter = editor.gutter
        if (gutter is EditorGutterComponentEx) {
            gutter.textAnnotations.forEach {
                if (it is AnnotationGutterProvider) {
                    return@attachProviderToEditor
                }
            }
        }

        editor.gutter.registerTextAnnotation(AnnotationGutterProvider(plugin))
    }

    protected fun buildIssuesForFile(state: AppStore, file: VirtualFile) {

    }
}