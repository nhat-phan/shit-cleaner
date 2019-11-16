package net.ntworld.intellijCodeCleaner.component.annotation

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorKind
import com.intellij.openapi.editor.ex.EditorGutterComponentEx
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.vfs.VirtualFile
import net.ntworld.intellijCodeCleaner.AppStore
import net.ntworld.intellijCodeCleaner.component.annotation.internal.AnnotationGutterDataUtil
import net.ntworld.redux.Dispatcher
import com.intellij.openapi.project.Project as IdeaProject

open class DefaultAnnotationManager(
    private val ideaProject: IdeaProject,
    private val dispatcher: Dispatcher<AppStore>,
    private val factory: AnnotationGutterDataFactory
) : AnnotationManager {
    override fun show() {
        val fileManager = FileEditorManager.getInstance(ideaProject)
        fileManager.selectedEditors.forEach { attach(it) }
    }

    override fun hide() {
        val fileManager = FileEditorManager.getInstance(ideaProject)
        fileManager.selectedEditors.forEach { detach(it) }
    }

    override fun attach(editor: FileEditor?) {
        if (null === editor) {
            return
        }

        if (editor is TextEditor) {
            return this.attachProvidersToEditor(editor.editor, editor.file)
        }
    }

    override fun detach(editor: FileEditor?) {
        if (null === editor) {
            return
        }

        if (editor is TextEditor) {
            val gutter = editor.editor.gutter
            if (gutter is EditorGutterComponentEx) {
                gutter.closeAllAnnotations()
            }
        }
    }

    protected open fun attachProvidersToEditor(editor: Editor, file: VirtualFile?) {
        if ((editor.editorKind != EditorKind.MAIN_EDITOR && editor.editorKind != EditorKind.UNTYPED) || null === file) {
            return
        }

        val gutter = editor.gutter
        if (gutter is EditorGutterComponentEx) {
            gutter.textAnnotations.forEach {
                if (it is AnnotationGutterProvider) {
                    return@attachProvidersToEditor
                }
            }
        }

        val data = this.factory.make(file)
        data.forEachIndexed { index, item ->
            editor.gutter.registerTextAnnotation(
                AnnotationGutterProvider(dispatcher, item, AnnotationGutterDataUtil.calcBackgroundOpacity(index, data.size))
            )
        }
    }

}