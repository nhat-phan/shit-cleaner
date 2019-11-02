package net.ntworld.intellijCodeCleaner.reducer

import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.action.CodeAnalyzedAction
import net.ntworld.intellijCodeCleaner.state.DataState
import net.ntworld.redux.Action
import net.ntworld.redux.Reducer

class DataReducer : Reducer<DataState>(DataState.Default) {

    override fun reduce(state: DataState, action: Action<*>): DataState {
        if (action !is CodeAnalyzedAction) {
            return state
        }
        val result = mutableMapOf<String, MutableList<DataState.Item>>()
        collect(result, DataState.ItemType.CODE_SMELL, action.payload.codeSmells)
        collect(result, DataState.ItemType.DUPLICATION, action.payload.duplications)
        return state.copy(items = result)
    }

    private fun collect(
        map: MutableMap<String, MutableList<DataState.Item>>,
        type: DataState.ItemType,
        issues: List<Issue>
    ) {
        issues.forEach {
            val item = DataState.Item(type = type, issue = it)
            val list: MutableList<DataState.Item>? = map[it.path]
            if (list === null) {
                map[it.path] = mutableListOf(item)
            } else {
                list.add(item)
            }
        }
    }

}