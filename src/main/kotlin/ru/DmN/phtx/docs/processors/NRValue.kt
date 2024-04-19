package ru.DmN.phtx.docs.processors

import ru.DmN.pht.processors.IStdNodeProcessor
import ru.DmN.phtx.docs.ast.NodeValue
import ru.DmN.siberia.processor.Processor
import ru.DmN.siberia.processor.ctx.ProcessingContext

object NRValue : IStdNodeProcessor<NodeValue> {
    override fun computeInt(node: NodeValue, processor: Processor, ctx: ProcessingContext): Int =
        node.value.toInt()

    override fun computeString(node: NodeValue, processor: Processor, ctx: ProcessingContext): String =
        node.value
}