package net.ntworld.codeCleaner.query

import net.ntworld.codeCleaner.structure.CodeQuality
import net.ntworld.foundation.cqrs.Query

interface FindCodeQualityByIdQuery: Query<CodeQuality> {
    val id: String

    companion object
}

