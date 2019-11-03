package net.ntworld.intellijCodeCleaner.component.annotation

import com.intellij.openapi.vfs.VirtualFile
import net.ntworld.codeCleaner.codeClimate.Lines
import net.ntworld.codeCleaner.codeClimate.Location
import net.ntworld.codeCleaner.structure.MaintainabilityRate

data class AnnotationData(
    val virtualFile: VirtualFile,
    val content: String,
    val issues: List<Item>
) {
    enum class ItemType {
        CODE_SMELL,
        DUPLICATION
    }

    data class Item(
        val projectId: String,
        val type: ItemType,
        val id: String,
        val path: String,
        val description: String,
        val lines: Lines,
        val locations: List<Location>,
        val rate: MaintainabilityRate
    )
}