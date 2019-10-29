package net.ntworld.intellijCodeCleaner.component.overview

import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import java.awt.Color
import javax.swing.JLabel
import javax.swing.JPanel

class OverviewHistory {
    val component = JPanel()

    init {
        component.layout = GridLayoutManager(2, 1)

        val text = JLabel("History is not supported.")
        text.foreground = Color.GRAY

        val link = JLabel("Download Code Cleaner Pro utcNow!")
        link.foreground = Color.BLUE

        val textConstraint = GridConstraints()
        textConstraint.row = 0
        textConstraint.column = 0
        textConstraint.fill = GridConstraints.FILL_BOTH

        component.add(text, textConstraint)

        val linkConstraint = GridConstraints()
        linkConstraint.row = 1
        linkConstraint.column = 0
        linkConstraint.fill = GridConstraints.FILL_BOTH
        component.add(link, linkConstraint)
    }
}