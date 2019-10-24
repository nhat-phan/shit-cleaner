package net.ntworld.codeCleaner.codeClimate.internal

import kotlinx.serialization.Serializable
import net.ntworld.codeCleaner.codeClimate.Location

@Serializable
internal data class LocationImpl(
    override val path: String,
    override val lines: LinesImpl
) : Location
