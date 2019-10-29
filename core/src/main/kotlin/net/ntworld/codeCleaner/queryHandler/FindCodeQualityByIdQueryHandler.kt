package net.ntworld.codeCleaner.queryHandler

import net.ntworld.codeCleaner.CodeQualityManager
import net.ntworld.codeCleaner.query.FindCodeQualityByIdQuery
import net.ntworld.codeCleaner.structure.CodeQuality
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.QueryHandler

@Handler
class FindCodeQualityByIdQueryHandler : QueryHandler<FindCodeQualityByIdQuery, CodeQuality> {

    override fun handle(query: FindCodeQualityByIdQuery): CodeQuality {
        return CodeQualityManager.find(query.id)
    }

}