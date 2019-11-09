package net.ntworld.intellijCodeCleaner.component.toolbar

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.ui.SimpleToolWindowPanel
import net.ntworld.codeCleaner.structure.MaintainabilityRate
import net.ntworld.intellijCodeCleaner.ComponentFactory
import javax.swing.JComponent

open class MainToolbar(componentFactory: ComponentFactory) {
    private val toolbar = DefaultActionGroup()

    init {
        toolbar.add(componentFactory.makeAnalyzeButton())
        toolbar.add(componentFactory.makeStopButton())
        toolbar.addSeparator()
        toolbar.add(componentFactory.makeMaintainabilityFilterButton(MaintainabilityRate.Bad))
        toolbar.add(componentFactory.makeMaintainabilityFilterButton(MaintainabilityRate.Moderate))
        toolbar.add(componentFactory.makeMaintainabilityFilterButton(MaintainabilityRate.Good))
        toolbar.addSeparator()
        toolbar.add(componentFactory.makeAnnotationToggleButton())
    }

    fun apply(content: JComponent): SimpleToolWindowPanel {
        val wrapper = SimpleToolWindowPanel(false, true)
        wrapper.toolbar = ActionManager.getInstance().createActionToolbar("toolbar", toolbar, false).component
        wrapper.setContent(content)
        return wrapper
    }
}