package net.ntworld.intellijCodeCleaner

import com.intellij.ide.util.TipUIUtil
import javax.swing.JComponent

class AboutProVersion {

    fun createComponent(): JComponent {
        val webview = TipUIUtil.createBrowser() as TipUIUtil.Browser
        webview.text = AboutProVersion::class.java.getResource("/about.html").readText()
        return webview.component
    }
}