package net.ntworld.intellijCodeCleaner

import net.ntworld.codeCleaner.CoreInfrastructure
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
        private val instance = MemorizedInfrastructure(IntellijCodeCleaner())

        operator fun invoke(): Infrastructure {
            return instance
        }
    }
}