package net.ntworld.intellijCodeCleaner.component.button

import net.ntworld.codeCleaner.structure.MaintainabilityRate
import javax.swing.Icon

class MaintainabilityFilterButton(override val rate: MaintainabilityRate, icon: Icon?) :
    AbstractMaintainabilityFilterButton(icon)