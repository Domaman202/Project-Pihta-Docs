package ru.DmN.phtx.docs.parsers

import ru.DmN.pht.parsers.NPValnB
import ru.DmN.phtx.docs.utils.node.NodeTypes
import ru.DmN.siberia.ast.Node
import ru.DmN.siberia.ast.NodeNodesList
import ru.DmN.siberia.lexer.Token
import ru.DmN.siberia.parser.Parser
import ru.DmN.siberia.parser.ctx.ParsingContext
import ru.DmN.siberia.parsers.INodeParser
import ru.DmN.siberia.utils.node.INodeInfo

object NPList : INodeParser {
    override fun parse(parser: Parser, ctx: ParsingContext, token: Token): Node =
        NPValnB.parse(parser, ctx) { NodeNodesList(INodeInfo.of(NodeTypes.LIST, ctx, token), it) }
}