package net.ntworld.codeCleaner.structure

import net.ntworld.codeCleaner.codeClimate.Lines
import net.ntworld.codeCleaner.codeClimate.Location

interface Issue {
    val id: String

    val path: String

    val lines: Lines

    val locations: List<Location>

    val description: String

    val content: String

    val point: Int

    val rate: MaintainabilityRate

    val severity: Severity
}