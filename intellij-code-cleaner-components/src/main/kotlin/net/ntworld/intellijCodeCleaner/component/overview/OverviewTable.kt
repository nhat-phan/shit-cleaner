package net.ntworld.intellijCodeCleaner.component.overview

import com.intellij.ui.table.JBTable
import net.ntworld.codeCleaner.statistic.CodeStatisticData
import net.ntworld.intellijCodeCleaner.state.ProjectState
import javax.swing.table.DefaultTableModel

class OverviewTable(
    prop: ProjectState
) {
    private val table = JBTable()

    val component: JBTable = table

    init {
        updateBy(prop)
    }

    fun updateBy(prop: ProjectState) {
        table.model = makeModel(prop)
        table.columnModel.getColumn(1).width = 150
        table.columnModel.getColumn(1).minWidth = 150
        table.columnModel.getColumn(1).maxWidth = 150
    }

    private fun makeModel(prop: ProjectState): DefaultTableModel {
        val model = DefaultTableModel()
        model.addColumn("Description")
        model.addColumn("")

        val codeStatisticData = prop.codeStatisticData

        if (null !== codeStatisticData) {
            fillCodeStatisticData(model, codeStatisticData)
        }
        return model
    }

    private fun fillCodeStatisticData(model: DefaultTableModel, data: CodeStatisticData) {
        if (data.languages.isEmpty()) {
            return
        }

        if (data.languages.size == 1) {
            model.addRow(arrayOf("Total line of ${data.languages.first().language} code", data.total.toString()))
            return
        }

        model.addRow(arrayOf("Total line of code", data.total.toString()))
        data.languages.forEach {
            model.addRow(
                arrayOf(
                    "    ${it.language}",
                    "${it.lines} (${Math.round(it.percent * 100) / 100}%)"
                )
            )
        }
    }
}