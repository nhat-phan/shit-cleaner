package net.ntworld.intellijCodeCleaner.ui

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.wm.ToolWindow
import com.intellij.util.IconUtil
import javax.swing.JPanel
import java.util.Calendar
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.JButton

open class CodeCleanerToolWindow(toolWindow: ToolWindow) {
    private var mainPanel: JPanel? = null
    private var button1: JButton? = null
    private var button2: JButton? = null
    private val wrapper = SimpleToolWindowPanel(false)

    init {
    }

    fun getContent(): JPanel? {
        wrapper.setContent(mainPanel!!)
        val toolbarGroup = DefaultActionGroup()
        wrapper.toolbar = ActionManager.getInstance().createActionToolbar("CodeCleaner", toolbarGroup, false).component
        return wrapper
    }
}