package ru.DmN.phtx.docs.utils.format

open class FmtString(
    val text: String,
    val fmt: List<Formatting>,
    val next: FmtString?
) {
    open fun toString(offset: Int): String {
        val sb = StringBuilder()
        for (i in 0 until offset)
            sb.append("| ")
        sb.append(text.replace(' ', '_'))
        sb.append(" [")
        for (it in fmt)
            sb.append(it.name[0])
        sb.append("]\n")
        next?.let { sb.append(it.toString(offset + 1)) }
        return sb.toString()
    }

    override fun toString(): String =
        toString(0)

    enum class Formatting {
        BOLD,
        ITALIC,
        REFERENCE
    }
}