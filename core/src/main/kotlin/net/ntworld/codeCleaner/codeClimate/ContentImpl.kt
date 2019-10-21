package net.ntworld.codeCleaner.codeClimate

import kotlinx.serialization.Serializable

@Serializable
internal data class ContentImpl(override val body: String) : Content
