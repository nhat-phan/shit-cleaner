package net.ntworld.env.query

import net.ntworld.foundation.cqrs.Query

interface IsExecutingQuery : Query<IsExecutingQueryResult> {
    val executeWatchdogId: String

    companion object
}
