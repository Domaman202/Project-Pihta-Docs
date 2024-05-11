package ru.DmN.phtx.docs

import ru.DmN.pht.module.utils.Module
import ru.DmN.pht.utils.addSNP
import ru.DmN.phtx.docs.utils.node.NodeTypes.*

object InstrHelper : Module("phtx/docs/instr/helper") {
    private fun initParsers() {
        // d
        addSNP(DESC)
        // e
        addSNP(EXAMPLE)
        // l
        addSNP(LONG)
        // s
        addSNP(SHORT)
        addSNP(SYMBOL)
        // t
        addSNP(TEST)
        // u
        addSNP(USAGE)
    }

    init {
        initParsers()
    }
}