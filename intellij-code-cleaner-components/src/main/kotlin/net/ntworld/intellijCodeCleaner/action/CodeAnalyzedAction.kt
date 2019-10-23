package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.CODE_ANALYZED

class CodeAnalyzedAction(projectId: String): AbstractProjectIdAction(projectId) {
    override val type: String = CODE_ANALYZED
}