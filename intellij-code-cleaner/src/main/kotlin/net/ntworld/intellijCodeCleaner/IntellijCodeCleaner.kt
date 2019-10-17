package net.ntworld.intellijCodeCleaner

import net.ntworld.codeClimate.CoreInfrastructure
import net.ntworld.foundation.IdGenerator
import net.ntworld.foundation.Infrastructure
import net.ntworld.foundation.InfrastructureProvider
import net.ntworld.foundation.MemorizedInfrastructure
import net.ntworld.foundation.util.UUIDGenerator
import kotlin.reflect.KClass

class IntellijCodeCleaner private constructor() : InfrastructureProvider() {
    private val included = listOf(
        CoreInfrastructure()
    )

    init {
        wire(this.root, this.included)
    }

    override fun <T : Any> idGeneratorOf(type: KClass<T>) = UUIDGenerator

    companion object {
        operator fun invoke(): Infrastructure {
            return MemorizedInfrastructure(IntellijCodeCleaner())
        }
    }
}