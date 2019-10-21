package net.ntworld.env.queryHandler

import net.ntworld.codeClimate.make
import net.ntworld.env.ExecuteWatchdogManager
import net.ntworld.env.query.IsExecutingQuery
import net.ntworld.env.query.IsExecutingQueryResult
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.QueryHandler

@Handler
class IsExecutingQueryHandler : QueryHandler<IsExecutingQuery, IsExecutingQueryResult> {

    override fun handle(query: IsExecutingQuery): IsExecutingQueryResult {
        return IsExecutingQueryResult.make(
            ExecuteWatchdogManager.isRunning(query.executeWatchdogId)
        )
    }

}