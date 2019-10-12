package net.ntworld.intellijCodeClimate.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import net.ntworld.codeClimate.command.AnalyzeCommand
import net.ntworld.codeClimate.make
import net.ntworld.intellijCodeClimate.IntellijCodeClimate

class HelloAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val codeClimate = IntellijCodeClimate()
        codeClimate {
            commandBus().process(AnalyzeCommand.make(path = null, engine = null))
        }
    }

}