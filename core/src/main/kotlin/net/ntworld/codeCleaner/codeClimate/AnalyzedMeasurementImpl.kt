package net.ntworld.codeCleaner.codeClimate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("measurement")
internal data class AnalyzedMeasurementImpl(
    override val name: String,

    override val value: Int,

    @SerialName("engine_name")
    override val engineName: String
) : AnalyzedMeasurement
