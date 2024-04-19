package ru.DmN.phtx.docs.parsers

import ru.DmN.phtx.docs.InstrHelper
import ru.DmN.phtx.docs.utils.node.NodeTypes.INSTR
import ru.DmN.siberia.ast.Node
import ru.DmN.siberia.ast.NodeNodesList
import ru.DmN.siberia.lexer.Token
import ru.DmN.siberia.parser.Parser
import ru.DmN.siberia.parser.ctx.ParsingContext
import ru.DmN.siberia.parsers.INodeParser
import ru.DmN.siberia.parsers.NPProgn
import ru.DmN.siberia.utils.node.INodeInfo

object NPInstruction : INodeParser {
    override fun parse(parser: Parser, ctx: ParsingContext, token: Token): Node {
        val context = ctx.subCtx()
        context.loadedModules.add(0, InstrHelper)
        return NPProgn.parse(parser, context) { NodeNodesList(INodeInfo.of(INSTR, context, token), it) }
    }
}