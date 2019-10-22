package net.ntworld.redux

abstract class Reducer<T: Any>(internal val initialState: T) {

    abstract fun reduce(state: T, action: Action<*>): T

}