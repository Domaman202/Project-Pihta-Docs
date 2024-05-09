package ru.DmN.phtx.docs.parsers

import ru.DmN.phtx.docs.ast.NodeValue
import ru.DmN.phtx.docs.ast.NodeValue.Type
import ru.DmN.phtx.docs.utils.node.NodeTypes.VALUE
import ru.DmN.siberia.ast.Node
import ru.DmN.siberia.lexer.Token
import ru.DmN.siberia.lexer.Token.DefaultType.INTEGER
import ru.DmN.siberia.lexer.Token.DefaultType.STRING
import ru.DmN.siberia.parser.Parser
import ru.DmN.siberia.parser.ctx.ParsingContext
import ru.DmN.siberia.parsers.INodeParser
import ru.DmN.siberia.utils.node.INodeInfo

object NPValue : INodeParser {
    override fun parse(parser: Parser, ctx: ParsingContext, token: Token): Node =
        NodeValue(
            INodeInfo.of(VALUE, ctx, token),
            when (token.type) {
                STRING  -> Type.STRING
                INTEGER -> Type.NUMBER
                else    -> throw RuntimeException()
            },
            token.text!!
        )
}