package ru.DmN.phtx.docs.processors

import ru.DmN.pht.processor.utils.computeList
import ru.DmN.pht.processor.utils.computeString
import ru.DmN.pht.utils.type
import ru.DmN.phtx.docs.ast.NodeInstruction
import ru.DmN.phtx.docs.ast.NodeInstruction.Usage
import ru.DmN.phtx.docs.ast.NodeInstruction.Usage.Argument
import ru.DmN.phtx.docs.utils.getTypeOr
import ru.DmN.phtx.docs.utils.node.NodeTypes.*
import ru.DmN.siberia.ast.NodeNodesList
import ru.DmN.siberia.processor.Processor
import ru.DmN.siberia.processor.ctx.ProcessingContext
import ru.DmN.siberia.processors.INodeProcessor

object NRInstruction : INodeProcessor<NodeNodesList> {
    override fun process(node: NodeNodesList, processor: Processor, ctx: ProcessingContext, valMode: Boolean): NodeInstruction =
        NodeInstruction(node.info.withType(INSTR_)).apply {
            node.nodes.forEach { it ->
                when (it.type) {
                    LONG    -> this.long    = processor.computeString(it, ctx)
                    SHORT   -> this.short   = processor.computeString(it, ctx)
                    SYMBOL  -> this.symbol  = processor.computeString(it, ctx)
                    DESC    -> this.desc    = processor.computeString(it, ctx)

                    USAGE -> {
                        val data = processor.computeList(it, ctx)
                        this.usage += Usage(processor.computeString(data[0], ctx)).apply {
                            data.stream().skip(1L).map { processor.computeList(it, ctx) }.forEach { it1 ->
                                this.arguments += Argument(processor.computeString(it1[1], ctx)).apply {
                                    this.names += processor.computeList(it1[0], ctx).asSequence().map { processor.computeString(it, ctx) }
                                }
                            }
                        }
                    }

                    EXAMPLE -> {
                        val data = processor.computeList(it, ctx)
                        if (processor.getTypeOr(data[0], ctx) == IValueProcessor.Type.LIST) {
                            this.examples += processor.computeList(data[0], ctx).map {
                                val pair = processor.computeList(it, ctx)
                                Pair(
                                    processor.computeString(pair[0], ctx),
                                    if (pair.size == 2)
                                        processor.computeString(pair[1], ctx)
                                    else null
                                )
                            }
                        } else {
                            this.examples += listOf(
                                Pair(
                                    processor.computeString(data[0], ctx),
                                    if (data.size == 2)
                                        processor.computeString(data[1], ctx)
                                    else null
                                )
                            )
                        }
                    }

                    TEST -> this.tests += processor.computeList(it, ctx).map { processor.computeString(it, ctx) }
                    else -> throw RuntimeException()
                }
            }
        }

    private fun processExample() {

    }
}