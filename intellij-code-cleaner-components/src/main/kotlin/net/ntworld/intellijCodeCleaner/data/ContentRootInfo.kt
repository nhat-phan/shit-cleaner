package net.ntworld.intellijCodeCleaner.data

import com.intellij.openapi.vfs.VirtualFile

data class ContentRootInfo(
    val name: String,
    val displayPath: String,
    val virtualFile: VirtualFile
)
