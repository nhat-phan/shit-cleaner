package net.ntworld.intellijCodeCleaner.ui

import com.intellij.find.FindModel
import com.intellij.find.impl.FindInProjectUtil
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.ToolWindow
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiManager
import com.intellij.ui.EditorTextField
import com.intellij.ui.OnePixelSplitter
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import com.intellij.usageView.UsageInfo
import com.intellij.usages.impl.UsagePreviewPanel
import com.intellij.util.IconUtil
import net.ntworld.intellijCodeCleaner.action.AnalyzeAction
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSplitPane

class CodeSmellsTabContent(private val project: Project) {
    var splitPane: JSplitPane? = null
    var issuesPanel: JPanel? = null
    var usagePreviewPanel: UsagePreviewPanel? = null

    fun getContent(): JPanel? {
        val codePreviewPanel = JPanel()
        codePreviewPanel.layout = GridLayoutManager(1, 1)
        usagePreviewPanel = UsagePreviewPanel(
            project,
            FindInProjectUtil.setupViewPresentation(false, FindModel())
        )
        val constraint = GridConstraints()
        constraint.row = 0
        constraint.column = 0
        constraint.fill = GridConstraints.FILL_BOTH

        codePreviewPanel.add(usagePreviewPanel, constraint)

        val splitter = OnePixelSplitter(false, "code.cleaner.code-smells", 0.5f)
        splitter.firstComponent = issuesPanel
        splitter.secondComponent = codePreviewPanel
        return splitter

//        val wrapper = SimpleToolWindowPanel(false)
//        wrapper.setContent(splitter)
//        val toolbarGroup = DefaultActionGroup()
////        val viewAction = ViewAction(this)
////        val helpAction = ViewAction(this)
////        val filterA = AnalyzeAction(null, null, IconLoader.findIcon("/icons/issue-file-filter-a.svg"))
////        val filterB = AnalyzeAction(null, null, IconLoader.findIcon("/icons/issue-file-filter-c.svg"))
////        val filterC = AnalyzeAction(null, null, IconLoader.findIcon("/icons/issue-file-filter-f.svg"))
////
//        toolbarGroup.add(AnalyzeAction)
//        toolbarGroup.addSeparator()
////        toolbarGroup.add(filterC)
////        toolbarGroup.add(filterB)
////        toolbarGroup.add(filterA)
////        toolbarGroup.addSeparator()
////        toolbarGroup.add(viewAction)
////        toolbarGroup.addSeparator()
////        toolbarGroup.add(helpAction)
////
//        wrapper.toolbar = ActionManager.getInstance().createActionToolbar("CodeCleaner", toolbarGroup, false).component
//        return wrapper
    }


    class ViewAction(private val tab: CodeSmellsTabContent) : AnAction() {
        private val path = ""
        private val testRelativePath = ""

        override fun actionPerformed(e: AnActionEvent) {
            val project = e.project!!
            val roots = ProjectRootManager.getInstance(project).contentRoots
            val infos = mutableListOf<UsageInfo>()
            for (root in roots) {
                if (root.path != path) {
                    continue
                }
                val file = root.findFileByRelativePath(testRelativePath)
                if (null !== file) {
                    val psiFile = PsiManager.getInstance(project).findFile(file)
                    if (null !== psiFile) {
                        val document = PsiDocumentManager.getInstance(project).getDocument(psiFile)!!
                        val startOffset = document.getLineStartOffset(14)
                        val endOffset = document.getLineStartOffset(19)
                        infos.add(UsageInfo(psiFile, startOffset, endOffset))
                    }
                }
            }
            tab.usagePreviewPanel!!.updateLayout(infos)

            // val document: Document = PsiDocumentManager.getInstance(project).
            // PsiDocumentManager.getInstance(project).getPsiFile(document)

        }

    }
}
