package net.ntworld.codeCleaner.queryHandler

import net.ntworld.codeCleaner.query.FindProjectByIdQuery
import net.ntworld.codeCleaner.structure.Project
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.QueryHandler

@Handler
class FindProjectByIdQueryHandler : QueryHandler<FindProjectByIdQuery, Project> {

    override fun handle(query: FindProjectByIdQuery): Project {
        TODO()
    }

}