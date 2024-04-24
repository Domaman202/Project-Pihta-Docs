package ru.DmN.phtx.docs.html.compilers

import ru.DmN.phtx.docs.ast.NodeNamedList
import ru.DmN.phtx.docs.html.utils.ctx.categories
import ru.DmN.phtx.docs.html.utils.ctx.category
import ru.DmN.phtx.docs.html.utils.ctx.instructions
import ru.DmN.siberia.compiler.Compiler
import ru.DmN.siberia.compiler.ctx.CompilationContext
import ru.DmN.siberia.compilers.INodeCompiler
import ru.DmN.siberia.compilers.NCDefault

object NCCategory : INodeCompiler<NodeNamedList> {
    override fun compile(node: NodeNamedList, compiler: Compiler, ctx: CompilationContext) {
        val context = ctx.subCtx()
        context.category = node.name
        context.instructions = ctx.categories.getOrPut(node.name) { ArrayList() }
        NCDefault.compile(node, compiler, context)
    }
}