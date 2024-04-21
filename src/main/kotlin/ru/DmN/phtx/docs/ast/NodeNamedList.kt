package ru.DmN.phtx.docs.ast

import ru.DmN.siberia.ast.Node
import ru.DmN.siberia.ast.NodeNodesList
import ru.DmN.siberia.utils.node.INodeInfo

class NodeNamedList(info: INodeInfo, nodes: MutableList<Node>, val name: String) : NodeNodesList(info, nodes)