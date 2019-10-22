package net.ntworld.codeCleaner.structure

interface OverviewNode {
    val text: String

    val children: List<OverviewNode>

    val hasData: Boolean

    companion object
}