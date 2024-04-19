package ru.DmN.phtx.docs.ast

import ru.DmN.siberia.ast.BaseNode
import ru.DmN.siberia.utils.node.INodeInfo

class NodeValue(info: INodeInfo, val type: Type, val value: String) : BaseNode(info) {
    enum class Type {
        STRING,
        NUMBER
    }
}