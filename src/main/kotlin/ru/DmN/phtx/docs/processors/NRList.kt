package ru.DmN.phtx.docs.processors

import ru.DmN.pht.processors.IStdNodeProcessor
import ru.DmN.siberia.ast.Node
import ru.DmN.siberia.ast.NodeNodesList
import ru.DmN.siberia.processor.Processor
import ru.DmN.siberia.processor.ctx.ProcessingContext

object NRList : IStdNodeProcessor<NodeNodesList> {
    override fun computeList(node: NodeNodesList, processor: Processor, ctx: ProcessingContext): List<Node> =
        node.nodes
}