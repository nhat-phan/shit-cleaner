package net.ntworld.intellijCodeCleaner.component.annotation

import com.intellij.openapi.vfs.VirtualFile

interface AnnotationGutterDataFactory {

    fun make(file: VirtualFile): List<AnnotationGutterData>

}