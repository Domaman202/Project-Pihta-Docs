package ru.DmN.phtx.docs.html.compilers

import ru.DmN.phtx.docs.ast.NodeNamedList
import ru.DmN.phtx.docs.html.utils.ctx.categories
import ru.DmN.phtx.docs.html.utils.ctx.category
import ru.DmN.phtx.docs.html.utils.ctx.instructions
import ru.DmN.siberia.compiler.Compiler
import ru.DmN.siberia.compiler.ctx.CompilationContext
import ru.DmN.siberia.compilers.INodeCompiler
import ru.DmN.siberia.compilers.NCDefault
import java.io.File

object NCCategory : INodeCompiler<NodeNamedList> {
    override fun compile(node: NodeNamedList, compiler: Compiler, ctx: CompilationContext) {
        val context = ctx.subCtx()
        val name = node.name.replace(" ", "_")
        context.category = name
        context.instructions = ctx.categories.getOrPut(name) { ArrayList() }
        NCDefault.compile(node, compiler, context)
    }
}