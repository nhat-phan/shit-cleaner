package net.ntworld.intellijCodeCleaner.ui

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.ui.SimpleToolWindowPanel
import net.ntworld.intellijCodeCleaner.action.*
import javax.swing.JPanel

/**
 * This is a toolbar on the right of all tabs. Actions:
 *
 * 1. Start analyzing
 * 2. Stop analyze process (disabled by default)
 * -----------------------
 * 3. grin-beam-sweat
 * 4. sad-tear
 * 5. angry
 * -----------------------
 * 6. Previous occurence navigator
 * 7. Next occurence navigator
 * 8. Autoscroll from source
 * 9. Toggle annotation (enabled by default)
 * -----------------------
 * 10. Support
 */
object MainToolbar {
    private val toolbar = DefaultActionGroup()

    init {
        toolbar.add(AnalyzeAction)
        toolbar.add(StopAnalyzeAction)
        toolbar.addSeparator()
        toolbar.add(IssueFilterFAction)
        toolbar.add(IssueFilterCAction)
        toolbar.add(IssueFilterAAction)
        toolbar.addSeparator()
        toolbar.add(ShowAnnotationAction)
    }

    fun apply(content: JPanel): SimpleToolWindowPanel {
        val wrapper = SimpleToolWindowPanel(false)
        wrapper.toolbar = ActionManager.getInstance().createActionToolbar("CodeCleaner", toolbar, false).component
        wrapper.setContent(content)
        return wrapper
    }
}