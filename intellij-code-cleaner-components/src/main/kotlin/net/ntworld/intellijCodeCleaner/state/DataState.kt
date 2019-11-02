package net.ntworld.intellijCodeCleaner.state

import net.ntworld.codeCleaner.structure.Issue

data class DataState(
    val items: Map<String, List<Item>>
) {
    enum class ItemType {
        CODE_SMELL,
        DUPLICATION
    }

    data class Item(
        val type: ItemType,
        val issue: Issue
    )

    companion object {
        val Default = DataState(mapOf())
    }
}
