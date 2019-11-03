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
        loopAndAssigns(
            file,
            allIssues,
            typeOfIssuesMap,
            store.project.codeSmells.values,
            AnnotationGutterData.ItemType.CODE_SMELL
        )
        loopAndAssigns(
            file,
            allIssues,
            typeOfIssuesMap,
            store.project.duplications.values,
            AnnotationGutterData.ItemType.DUPLICATION
        )
        allIssues.sortBy { it.numberOfLines }

        val pair = AnnotationGutterDataUtil.groupLinesAndIssues(store.project.id, allIssues, typeOfIssuesMap)
        return pair.first.mapIndexed { index, it ->
            AnnotationGutterData(
                virtualFile = file,
                content = contents,
                issues = pair.second[index],
                lines = it
            )
        }
    }

    private fun loopAndAssigns(
        file: VirtualFile,
        allIssues: MutableList<Issue>,
        typeOfIssuesMap: MutableMap<String, AnnotationGutterData.ItemType>,
        issues: Collection<Issue>,
        type: AnnotationGutterData.ItemType
    ) {
        issues
            .filter { store.project.basePath + File.separator + it.path == file.path }
            .forEach {
                allIssues.add(it)
                typeOfIssuesMap[it.id] = type
            }
    }

}
