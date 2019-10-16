package net.ntworld.intellijCodeCleaner

import net.ntworld.codeClimate.CoreInfrastructure
import net.ntworld.foundation.Infrastructure
import net.ntworld.foundation.InfrastructureProvider
import net.ntworld.foundation.MemorizedInfrastructure

class IntellijCodeCleaner private constructor() : InfrastructureProvider() {
    private val included = listOf(
        CoreInfrastructure()
    )

    init {
        wire(this.root, this.included)
    }

    companion object {
        operator fun invoke(): Infrastructure {
            return MemorizedInfrastructure(IntellijCodeCleaner())
        }
    }
}