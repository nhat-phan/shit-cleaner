package net.ntworld.intellijCodeCleaner

import com.intellij.ide.util.TipUIUtil
import com.intellij.ui.ScrollPaneFactory
import javax.swing.JComponent

class Help {

    fun createComponent(): JComponent {
        val webview = TipUIUtil.createBrowser() as TipUIUtil.Browser
        webview.text = Help::class.java.getResource("/help.html").readText()
        return ScrollPaneFactory.createScrollPane(webview.component)
    }
}