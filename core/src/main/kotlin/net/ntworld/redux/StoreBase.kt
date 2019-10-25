package net.ntworld.redux

import kotlin.reflect.KProperty

open class StoreBase : Store {
    private val reducers = mutableMapOf<String, Reducer<Any>>()
    private val states = mutableMapOf<String, Any>()
    private val listeners = mutableMapOf<String, MutableSet<() -> Unit>>()
    private val globalListeners = mutableSetOf<() -> Unit>()

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> reducer(reducer: Reducer<T>): Delegate<T> {
        return Delegate(reducer)
    }

    private fun triggerListeners(key: String) {
        val set = listeners[key]
        if (null !== set) {
            set.forEach { it.invoke() }
        }
    }

    private fun triggerGlobalListeners() {
        globalListeners.forEach { it.invoke() }
    }

    override fun onChange(key: String, block: () -> Unit) {
        val set = listeners[key]
        if (null === set) {
            listeners[key] = mutableSetOf(block)
        } else {
            set.add(block)
        }
    }

    override fun onChange(block: () -> Unit) {
        globalListeners.add(block)
    }

    override fun reduce(action: Action<*>): Boolean {
        var changed = false
        reducers.forEach { (key, reducer) ->
            val state = states[key]
            val newState = reducer.doReduce(state!!, action)
            if (state !== newState) {
                changed = true
                states[key] = newState
                triggerListeners(key)
            }
        }
        if (changed) {
            triggerGlobalListeners()
        }
        return changed
    }

    class Delegate<T : Any>(private val reducer: Reducer<T>) {
        @Suppress("UNCHECKED_CAST")
        operator fun provideDelegate(
            ref: StoreBase,
            prop: KProperty<*>
        ): Delegate<T> {
            ref.states[prop.name] = reducer.initialState
            ref.reducers[prop.name] = reducer as Reducer<Any>
            return this
        }

        @Suppress("UNCHECKED_CAST")
        operator fun getValue(ref: StoreBase, property: KProperty<*>): T {
            return ref.states[property.name] as T
        }
    }
}
