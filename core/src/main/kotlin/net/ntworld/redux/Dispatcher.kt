package net.ntworld.redux

interface Dispatcher<S : Store> {
    val store: S

    operator fun <T : Map<String, Any>> invoke(action: Action<T>) = dispatch(action)

    infix fun <T : Map<String, Any>> dispatch(action: Action<T>) {
        store.reduce(action)
    }

}