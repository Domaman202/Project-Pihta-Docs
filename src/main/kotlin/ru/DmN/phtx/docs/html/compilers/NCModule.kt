package ru.DmN.phtx.docs.html.compilers

import ru.DmN.phtx.docs.ast.NodeNamedList
import ru.DmN.phtx.docs.html.utils.ctx.categories
import ru.DmN.phtx.docs.html.utils.ctx.doc_module
import ru.DmN.phtx.docs.html.utils.ctx.modules
import ru.DmN.siberia.compiler.Compiler
import ru.DmN.siberia.compiler.ctx.CompilationContext
import ru.DmN.siberia.compilers.INodeCompiler
import ru.DmN.siberia.compilers.NCDefault
import java.io.File

object NCModule : INodeCompiler<NodeNamedList> {
    override fun compile(node: NodeNamedList, compiler: Compiler, ctx: CompilationContext) {
        val context = ctx.subCtx()
        context.doc_module = node.name
        context.categories = compiler.modules.getOrPut(node.name) { HashMap() }
        NCDefault.compile(node, compiler, context)
    }
}