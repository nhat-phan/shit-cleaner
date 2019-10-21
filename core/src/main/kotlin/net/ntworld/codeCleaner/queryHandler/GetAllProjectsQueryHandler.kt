package net.ntworld.codeCleaner.queryHandler

import net.ntworld.codeCleaner.query.GetAllProjectsQuery
import net.ntworld.codeCleaner.query.GetAllProjectsQueryResult
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.QueryHandler

@Handler
class GetAllProjectsQueryHandler : QueryHandler<GetAllProjectsQuery, GetAllProjectsQueryResult> {

    override fun handle(query: GetAllProjectsQuery): GetAllProjectsQueryResult {
        TODO()
    }

}