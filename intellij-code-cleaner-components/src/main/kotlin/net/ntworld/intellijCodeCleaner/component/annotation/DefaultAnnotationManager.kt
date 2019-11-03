package net.ntworld.intellijCodeCleaner.component.annotation

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorKind
import com.intellij.openapi.editor.ex.EditorGutterComponentEx
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.vfs.VirtualFile
import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.AppStore
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.readContent
import java.io.File
import com.intellij.openapi.project.Project as IdeaProject

open class DefaultAnnotationManager(
    private val ideaProject: IdeaProject,
    private val plugin: Plugin
) : AnnotationManager {
    override fun show() {
        val fileManager = FileEditorManager.getInstance(ideaProject)
        fileManager.selectedEditors.forEach { attach(it) }
    }

    override fun hide() {
        TODO("not implemented")
    }

    override fun attach(editor: FileEditor?) {
        if (null === editor) {
            return
        }

        if (editor is TextEditor) {
            return this.attachProviderToEditor(editor.editor, editor.file)
        }
    }

    override fun detach(editor: FileEditor?) {
        TODO()
    }

    protected open fun attachProviderToEditor(editor: Editor, file: VirtualFile?) {
        if (editor.editorKind != EditorKind.MAIN_EDITOR &&
            editor.editorKind != EditorKind.UNTYPED &&
            null !== file
        ) {
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

        val data = buildAnnotationDataForFile(plugin.store, file!!)
        if (null !== data && data.issues.isNotEmpty()) {
            editor.gutter.registerTextAnnotation(AnnotationGutterProvider(plugin, data))
        }
    }

    protected open fun buildAnnotationDataForFile(store: AppStore, file: VirtualFile): AnnotationData? {
        val content = file.readContent()
        val issues = mutableListOf<AnnotationData.Item>()
        buildIssues(
            issues, store.project.id, store.project.basePath, file.path,
            AnnotationData.ItemType.CODE_SMELL,
            store.project.codeSmells.values
        )
        buildIssues(
            issues, store.project.id, store.project.basePath, file.path,
            AnnotationData.ItemType.DUPLICATION,
            store.project.duplications.values
        )
        return if (issues.isEmpty()) null else AnnotationData(
            virtualFile = file,
            content = content,
            issues = issues
        )
    }

    protected open fun buildIssues(
        result: MutableList<AnnotationData.Item>,
        projectId: String,
        basePath: String,
        filePath: String,
        type: AnnotationData.ItemType,
        collection: Collection<Issue>
    ) = collection.forEach {
        val path = basePath + File.separator + it.path
        if (path == filePath) {
            result.add(
                AnnotationData.Item(
                    type = type,
                    projectId = projectId,
                    id = it.id,
                    path = it.path,
                    description = it.description,
                    lines = it.lines,
                    locations = it.locations,
                    rate = it.rate
                )
            )
        }
    }
}