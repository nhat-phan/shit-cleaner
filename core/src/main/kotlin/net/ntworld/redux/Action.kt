package net.ntworld.redux

interface Action<T : Map<String, Any>> {
    val type: String

    val payload: T?

    interface EmptyPayload : Action<Map<String, Any>> {
        override val payload: Map<String, Any>?
            get() = null
    }
}