package net.ntworld.codeCleaner.codeClimate.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.ntworld.codeCleaner.codeClimate.AnalyzedMeasurement

@Serializable
@SerialName("measurement")
internal data class AnalyzedMeasurementImpl(
    override val name: String,

    override val value: Int,

    @SerialName("engine_name")
    override val engineName: String
) : AnalyzedMeasurement
