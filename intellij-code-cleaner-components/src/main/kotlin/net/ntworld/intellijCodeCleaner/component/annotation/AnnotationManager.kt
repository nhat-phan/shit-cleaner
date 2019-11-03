package net.ntworld.intellijCodeCleaner.component.annotation

import com.intellij.openapi.fileEditor.FileEditor

interface AnnotationManager {
    fun show()

    fun hide()

    fun attach(editor: FileEditor?)

    fun detach(editor: FileEditor?)
}
