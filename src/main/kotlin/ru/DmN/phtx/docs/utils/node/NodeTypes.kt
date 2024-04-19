package ru.DmN.phtx.docs.utils.node

import ru.DmN.siberia.utils.node.INodeType
import ru.DmN.siberia.utils.node.NodeTypes.Type
import ru.DmN.siberia.utils.node.NodeTypes.Type.PARSED
import ru.DmN.siberia.utils.node.NodeTypes.Type.PROCESSED

enum class NodeTypes : INodeType {
    // c
    CATEGORY("category", PARSED),
    CATEGORY_("category", PROCESSED),
    // d
    DESC("desc", PARSED),
    // e
    EXAMPLE("example", PARSED),
    // i
    INSTR("instr", PARSED),
    INSTR_("instr", PROCESSED),
    // l
    LIST("list", PARSED),
    LONG("long", PARSED),
    // s
    SHORT("short", PARSED),
    SYMBOL("symbol", PARSED),
    // u
    USAGE("usage", PARSED),
    // v
    VALUE("value", PARSED);

    override val operation: String
    override val processable: Boolean
    override val compilable: Boolean

    constructor(operation: String, processable: Boolean, compilable: Boolean) {
        this.operation = operation
        this.processable = processable
        this.compilable = compilable
    }

    constructor(operation: String, type: Type) {
        this.operation = operation
        if (type == PARSED) {
            this.processable = true
            this.compilable = false
        } else {
            this.processable = false
            this.compilable = true
        }
    }
}