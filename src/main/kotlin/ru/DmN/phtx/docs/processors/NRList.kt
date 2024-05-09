package ru.DmN.phtx.docs.processors

import ru.DmN.pht.processors.IStdNodeProcessor
import ru.DmN.phtx.docs.processors.IValueProcessor.Type.LIST
import ru.DmN.siberia.ast.Node
import ru.DmN.siberia.ast.NodeNodesList
import ru.DmN.siberia.processor.Processor
import ru.DmN.siberia.processor.ctx.ProcessingContext

object NRList : IStdNodeProcessor<NodeNodesList>, IValueProcessor<NodeNodesList> {
    override fun computeList(node: NodeNodesList, processor: Processor, ctx: ProcessingContext): List<Node> =
        node.nodes

    override fun getType(node: NodeNodesList, processor: Processor, ctx: ProcessingContext): IValueProcessor.Type =
        LIST
}