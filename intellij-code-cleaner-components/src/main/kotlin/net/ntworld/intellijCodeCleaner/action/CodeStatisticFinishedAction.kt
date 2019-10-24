package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.CODE_STATISTIC_FINISHED

class CodeStatisticFinishedAction(projectId: String): AbstractProjectIdAction(projectId) {
    override val type: String = CODE_STATISTIC_FINISHED
}