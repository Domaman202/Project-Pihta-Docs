package ru.DmN.phtx.docs.utils

import ru.DmN.phtx.docs.processors.IValueProcessor
import ru.DmN.siberia.ast.Node
import ru.DmN.siberia.processor.Processor
import ru.DmN.siberia.processor.ctx.ProcessingContext

fun Processor.getType(node: Node, ctx: ProcessingContext): IValueProcessor.Type =
    getTypeOr(node, ctx)!!

fun Processor.getTypeOr(node: Node, ctx: ProcessingContext): IValueProcessor.Type? =
    get(node, ctx).let {
        if (it is IValueProcessor) {
            it.getType(node, this, ctx)
        } else null
    }

inline fun <T, R> List<T>.dropMap(drop: Int, block: (e: T) -> R): MutableList<R> {
    val list = ArrayList<R>()
    for (i in drop until size)
        list += block(this[i])
    return list
}