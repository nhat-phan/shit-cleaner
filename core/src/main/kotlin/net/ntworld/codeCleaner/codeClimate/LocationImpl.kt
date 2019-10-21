package net.ntworld.codeCleaner.codeClimate

import kotlinx.serialization.Serializable

@Serializable
internal data class LocationImpl(
    override val path: String,
    override val lines: LinesImpl
) : Location
