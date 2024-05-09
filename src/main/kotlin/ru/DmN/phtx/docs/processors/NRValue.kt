package ru.DmN.phtx.docs.processors

import ru.DmN.pht.processors.IStdNodeProcessor
import ru.DmN.pht.utils.type
import ru.DmN.phtx.docs.ast.NodeValue
import ru.DmN.phtx.docs.ast.NodeValue.Type.*
import ru.DmN.phtx.docs.processors.IValueProcessor.Type
import ru.DmN.siberia.ast.Node
import ru.DmN.siberia.processor.Processor
import ru.DmN.siberia.processor.ctx.ProcessingContext

object NRValue : IStdNodeProcessor<NodeValue>, IValueProcessor<NodeValue> {
    override fun computeInt(node: NodeValue, processor: Processor, ctx: ProcessingContext): Int =
        node.value.toInt()

    override fun computeString(node: NodeValue, processor: Processor, ctx: ProcessingContext): String =
        node.value

    override fun getType(node: NodeValue, processor: Processor, ctx: ProcessingContext): Type =
        when (node.type) {
            STRING -> Type.STRING
            NUMBER -> Type.NUMBER
            else   -> Type.OTHER
        }
}