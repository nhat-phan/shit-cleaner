package net.ntworld.codeCleaner.structure

interface OverviewNode {
    val text: String

    val children: List<OverviewNode>

    val icon: String

    val hasData: Boolean

    companion object
}