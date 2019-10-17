package net.ntworld.env.query

import net.ntworld.foundation.cqrs.QueryResult

interface IsExecutingQueryResult: QueryResult {
    val value: Boolean

    companion object
}
