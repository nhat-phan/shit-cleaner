package net.ntworld.intellijCodeCleaner

import net.ntworld.intellijCodeCleaner.component.AnalyzeMenuActionBase

class AnalyzeMenuAction : AnalyzeMenuActionBase() {
    override val componentFactory: ComponentFactory = DefaultComponentFactory
}