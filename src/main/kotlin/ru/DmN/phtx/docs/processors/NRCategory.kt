package ru.DmN.phtx.docs.processors

import ru.DmN.pht.processor.utils.computeString
import ru.DmN.phtx.docs.ast.NodeCategory
import ru.DmN.phtx.docs.utils.dropMap
import ru.DmN.phtx.docs.utils.node.NodeTypes.CATEGORY_
import ru.DmN.siberia.ast.NodeNodesList
import ru.DmN.siberia.processor.Processor
import ru.DmN.siberia.processor.ctx.ProcessingContext
import ru.DmN.siberia.processors.INodeProcessor

object NRCategory : INodeProcessor<NodeNodesList> {
    override fun process(node: NodeNodesList, processor: Processor, ctx: ProcessingContext, valMode: Boolean): NodeCategory =
        NodeCategory(node.info.withType(CATEGORY_), node.nodes.dropMap(1) { processor.process(it, ctx, false)!! }, processor.computeString(node.nodes[0], ctx))
}