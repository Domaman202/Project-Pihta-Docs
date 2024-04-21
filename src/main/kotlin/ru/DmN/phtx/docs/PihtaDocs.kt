package ru.DmN.phtx.docs

import ru.DmN.pht.module.utils.Module
import ru.DmN.pht.utils.addNP
import ru.DmN.pht.utils.addSNP
import ru.DmN.pht.utils.addSNU
import ru.DmN.phtx.docs.parsers.NPInstruction
import ru.DmN.phtx.docs.parsers.NPList
import ru.DmN.phtx.docs.parsers.NPValue
import ru.DmN.phtx.docs.processors.*
import ru.DmN.phtx.docs.utils.Platforms.HTML
import ru.DmN.phtx.docs.utils.node.NodeTypes.*
import ru.DmN.siberia.parser.Parser
import ru.DmN.siberia.parser.ctx.ParsingContext
import ru.DmN.siberia.processor.utils.platform

object PihtaDocs : Module("phtx/docs") {
    private fun initParsers() {
        // c
        addSNP(CATEGORY)
        // i
        addNP("instr",  NPInstruction)
        // l
        addNP("valn!",  NPList)
        // m
        addSNP(MODULE)
        // v
        addNP("value!", NPValue)
    }

    private fun initUnparsers() {
        // c
        addSNU(CATEGORY)
        // CATEGORY_
        // d
        addSNU(DESC)
        // e
        addSNU(EXAMPLE)
        // i
        addSNU(INSTR)
        // INSTR_
        // l
        addSNU(LIST)
        addSNU(LONG)
        // m
        addSNU(MODULE)
        // MODULE_
        // s
        addSNU(SHORT)
        addSNU(SYMBOL)
        // u
        addSNU(USAGE)
        // v
        addSNU(VALUE)
    }

    private fun initProcessors() {
        // c
        add(CATEGORY, NRCategory)
        // d
        add(DESC,     NRAttribute)
        // e
        add(EXAMPLE,  NRAttribute)
        // i
        add(INSTR,    NRInstruction)
        // l
        add(LIST,     NRList)
        add(LONG,     NRAttribute)
        // m
        add(MODULE,   NRModule)
        // s
        add(SHORT,    NRAttribute)
        add(SYMBOL,   NRAttribute)
        // u
        add(USAGE,    NRAttribute)
        // v
        add(VALUE,    NRValue)
    }

    override fun load(parser: Parser, ctx: ParsingContext, uses: MutableList<String>): Boolean {
        if (!ctx.loadedModules.contains(this)) {
            when (ctx.platform) {
                HTML -> uses += "phtx/docs/html"
            }
            super.load(parser, ctx, uses)
        }
        return false
    }

    init {
        initParsers()
        initUnparsers()
        initProcessors()
    }
}