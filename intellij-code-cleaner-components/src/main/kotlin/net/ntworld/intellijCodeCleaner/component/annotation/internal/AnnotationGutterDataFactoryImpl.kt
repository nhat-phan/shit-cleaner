package net.ntworld.intellijCodeCleaner.component.annotation.internal

import com.intellij.openapi.vfs.VirtualFile
import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.AppStore
import net.ntworld.intellijCodeCleaner.component.annotation.AnnotationGutterData
import net.ntworld.intellijCodeCleaner.component.annotation.AnnotationGutterDataFactory
import net.ntworld.intellijCodeCleaner.readLines
import java.io.File

class AnnotationGutterDataFactoryImpl(
    private val store: AppStore
) : AnnotationGutterDataFactory {

    override fun make(file: VirtualFile): List<AnnotationGutterData> {
        val contents = file.readLines()
        val allIssues = mutableListOf<Issue>()
        val typeOfIssuesMap = mutableMapOf<String, AnnotationGutterData.ItemType>()
        val issueFilter: (String) -> Boolean = {
            store.project.basePath + File.separator + it == file.path
        }
        AnnotationGutterDataUtil.loopAndAssigns(
            issueFilter,
            allIssues,
            typeOfIssuesMap,
            store.project.codeSmells.values,
            AnnotationGutterData.ItemType.CODE_SMELL
        )
        AnnotationGutterDataUtil.loopAndAssigns(
            issueFilter,
            allIssues,
            typeOfIssuesMap,
            store.project.duplications.values,
            AnnotationGutterData.ItemType.DUPLICATION
        )
        allIssues.sortByDescending { it.numberOfLines }

        val pair = AnnotationGutterDataUtil.groupLinesAndIssues(store.project.id, allIssues, typeOfIssuesMap)
        return pair.first.mapIndexed { index, it ->
            AnnotationGutterData(
                virtualFile = file, content = contents, issues = pair.second[index], lines = it
            )
        }
    }
}
