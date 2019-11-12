package net.ntworld.codeCleaner

import net.ntworld.codeCleaner.quality.CodeQualityParser
import net.ntworld.codeCleaner.structure.CodeQuality
import net.ntworld.codeCleaner.structure.generated.CodeQualityImpl

object CodeQualityManager {
    private val data: MutableMap<String, CodeQuality> = mutableMapOf()

    fun create(
        id: String,
        projectId: String,
        analyzeProcessStartAt: String,
        analyzeProcessEndAt: String,
        raw: String
    ) {
        val pair = CodeQualityParser.parse(raw)
        data[id] = CodeQualityImpl(
            id = id,
            projectId = projectId,
            codeSmells = pair.first,
            duplications = pair.second,
            analyzeProcessStartAt = analyzeProcessStartAt,
            analyzeProcessEndAt = analyzeProcessEndAt,
            createdAt = Util.utcNow()
        )
    }

    fun find(id: String): CodeQuality {
        return data[id]!!
    }
}