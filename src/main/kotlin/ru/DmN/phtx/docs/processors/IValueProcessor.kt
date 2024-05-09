package ru.DmN.phtx.docs.processors

import ru.DmN.siberia.ast.Node
import ru.DmN.siberia.processor.Processor
import ru.DmN.siberia.processor.ctx.ProcessingContext
import ru.DmN.siberia.processors.INodeProcessor

interface IValueProcessor<T : Node> : INodeProcessor<T> {
    fun getType(node: T, processor: Processor, ctx: ProcessingContext): Type

    enum class Type {
        LIST,
        STRING,
        NUMBER,
        OTHER
    }
}