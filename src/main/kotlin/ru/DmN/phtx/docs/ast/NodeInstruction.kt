package ru.DmN.phtx.docs.ast

import ru.DmN.siberia.ast.BaseNode
import ru.DmN.siberia.utils.node.INodeInfo

class NodeInstruction(info: INodeInfo) : BaseNode(info) {
    var long: String? = null
    var short: String? = null
    var symbol: String? = null
    //
    var desc: String? = null
    //
    var usage: MutableList<Usage> = ArrayList()
    var examples: MutableList<Example> = ArrayList()

    class Usage(val code: String) {
        val arguments: MutableList<Argument> = ArrayList()

        class Argument(val desc: String) {
            val names: MutableList<String> = ArrayList()
        }
    }

    class Example(val code: String) {
        var desc: String? = null
    }
}