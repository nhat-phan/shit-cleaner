package net.ntworld.codeCleaner.codeClimate.internal

import kotlinx.serialization.Serializable
import net.ntworld.codeCleaner.codeClimate.Lines

@Serializable
internal data class LinesImpl(override val begin: Int, override val end: Int) :
    Lines