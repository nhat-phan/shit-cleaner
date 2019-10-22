package net.ntworld.codeCleaner.queryHandler

import net.ntworld.codeCleaner.CoreInfrastructure
import net.ntworld.codeCleaner.make
import net.ntworld.codeCleaner.query.FindProjectStateByIdQuery
import net.ntworld.codeCleaner.structure.ProjectState
import net.ntworld.env.query.IsExecutingQuery
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.QueryHandler

@Handler
class FindProjectStateByIdQueryHandler(
    private val infrastructure: CoreInfrastructure
) : QueryHandler<FindProjectStateByIdQuery, ProjectState> {

    override fun handle(query: FindProjectStateByIdQuery): ProjectState {
        val isRunning = infrastructure.queryBus().process(
            IsExecutingQuery.make(query.id)
        )

        return ProjectState.make(
            projectId = query.id,
            isRunning = isRunning.value
        )
    }

}