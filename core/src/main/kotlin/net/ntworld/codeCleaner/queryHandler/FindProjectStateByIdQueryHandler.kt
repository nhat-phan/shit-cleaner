package net.ntworld.codeCleaner.queryHandler

import net.ntworld.codeCleaner.query.FindProjectStateByIdQuery
import net.ntworld.codeCleaner.structure.ProjectState
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.QueryHandler

@Handler
class FindProjectStateByIdQueryHandler : QueryHandler<FindProjectStateByIdQuery, ProjectState> {

    override fun handle(query: FindProjectStateByIdQuery): ProjectState {
        TODO()
    }

}