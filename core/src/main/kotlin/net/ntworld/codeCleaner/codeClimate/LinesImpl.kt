package net.ntworld.codeCleaner.codeClimate

import kotlinx.serialization.Serializable

@Serializable
internal data class LinesImpl(override val begin: Int, override val end: Int) : Lines