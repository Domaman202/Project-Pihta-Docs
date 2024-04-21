package ru.DmN.phtx.docs.html

import ru.DmN.phtx.docs.html.compilers.NCCategory
import ru.DmN.phtx.docs.html.compilers.NCInstruction
import ru.DmN.phtx.docs.html.compilers.NCModule
import ru.DmN.phtx.docs.html.utils.ctx.isModules
import ru.DmN.phtx.docs.html.utils.ctx.modules
import ru.DmN.phtx.docs.utils.Platforms.HTML
import ru.DmN.phtx.docs.utils.node.NodeTypes.*
import ru.DmN.siberia.compiler.Compiler
import ru.DmN.siberia.compiler.ctx.CompilationContext
import ru.DmN.siberia.compiler.utils.ModuleCompilers
import java.util.*

object PhtDocsHtml : ModuleCompilers("phtx/docs/html", HTML) {
    private fun initCompilers() {
        // c
        add(CATEGORY_, NCCategory)
        // i
        add(INSTR_,    NCInstruction)
        // m
        add(MODULE_,   NCModule)
    }

    override fun load(compiler: Compiler, ctx: CompilationContext) {
        if (!ctx.loadedModules.contains(this)) {
            if (!compiler.isModules)
                compiler.modules = TreeMap()
            super.load(compiler, ctx)
        }
    }

    init {
        initCompilers()
    }
}