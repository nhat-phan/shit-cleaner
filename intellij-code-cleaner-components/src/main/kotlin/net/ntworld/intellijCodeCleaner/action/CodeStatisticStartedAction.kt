package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.CODE_STATISTIC_STARTED

class CodeStatisticStartedAction(projectId: String): AbstractProjectIdAction(projectId) {
    override val type: String = CODE_STATISTIC_STARTED
}