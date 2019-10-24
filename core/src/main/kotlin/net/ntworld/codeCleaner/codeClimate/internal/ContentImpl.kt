package net.ntworld.codeCleaner.codeClimate.internal

import kotlinx.serialization.Serializable
import net.ntworld.codeCleaner.codeClimate.Content

@Serializable
internal data class ContentImpl(override val body: String) : Content
