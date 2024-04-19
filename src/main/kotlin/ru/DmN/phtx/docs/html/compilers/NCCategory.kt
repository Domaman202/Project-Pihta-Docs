package ru.DmN.phtx.docs.html.compilers

import ru.DmN.phtx.docs.ast.NodeCategory
import ru.DmN.phtx.docs.html.utils.ctx.categories
import ru.DmN.phtx.docs.html.utils.ctx.instructions
import ru.DmN.siberia.compiler.Compiler
import ru.DmN.siberia.compiler.ctx.CompilationContext
import ru.DmN.siberia.compilers.INodeCompiler
import ru.DmN.siberia.compilers.NCDefault

object NCCategory : INodeCompiler<NodeCategory> {
    override fun compile(node: NodeCategory, compiler: Compiler, ctx: CompilationContext) {
        val context = ctx.subCtx()
        context.instructions = compiler.categories.getOrPut(node.name) { ArrayList() }
        NCDefault.compile(node, compiler, context)
    }
}