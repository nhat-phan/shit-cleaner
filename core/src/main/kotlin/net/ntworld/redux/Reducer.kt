package net.ntworld.redux

abstract class Reducer<T : Any>(internal val initialState: T) {
    protected open val log: Boolean = false

    abstract fun reduce(state: T, action: Action<*>): T

    internal fun doReduce(state: T, action: Action<*>): T {
        return if (log) this.reduceWithLog(state, action) else this.reduce(state, action)
    }

    private fun reduceWithLog(state: T, action: Action<*>): T {
        this.logBegin(state, action)
        val out = reduce(state, action)
        this.logEnd(out)

        return out
    }

    protected open fun logBegin(state: T, action: Action<*>) {
        log("------------------------")
        logState(state, false)
        logAction(action)
    }

    protected open fun logState(state: T, output: Boolean) {
        if (output) {
            log("Result: $state")
        } else {
            log("State: $state")
        }
    }

    protected open fun logAction(action: Action<*>) {
        log("Action: $action")
    }

    protected open fun logEnd(out: T) {
        logState(out, true)
        log("------------------------")
    }

    protected open fun log(text: String) {
        println("${this::class}: $text")
    }
}