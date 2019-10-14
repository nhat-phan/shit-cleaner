package net.ntworld.codeClimate.structure

import kotlinx.serialization.Serializable

@Serializable
internal data class ContentImpl(override val body: String) : Content
