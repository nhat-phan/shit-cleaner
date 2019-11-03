package net.ntworld.intellijCodeCleaner.component.annotation

import com.intellij.openapi.vfs.VirtualFile
import net.ntworld.codeCleaner.structure.MaintainabilityRate

data class AnnotationGutterData(
    val virtualFile: VirtualFile,
    val content: List<String>,
    val issues: Map<String, Item>,
    val lines: Map<Int, Line>
) {
    enum class ItemType {
        CODE_SMELL,
        DUPLICATION
    }

    data class Line(
        val start: Boolean,
        val original: Boolean,
        val issueId: String
    )

    data class Item(
        val id: String,
        val projectId: String,
        val type: ItemType,
        val description: String,
        val rate: MaintainabilityRate
    )
}