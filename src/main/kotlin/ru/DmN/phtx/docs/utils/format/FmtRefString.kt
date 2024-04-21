package ru.DmN.phtx.docs.utils.format

class FmtRefString(text: String, fmt: List<Formatting>, next: FmtString?, val ref: String) : FmtString(text, fmt, next) {
    override fun toString(offset: Int): String {
        val sb = StringBuilder()
        for (i in 0 until offset)
            sb.append("| ")
        sb.append(text.replace(' ', '_'))
        sb.append(" (").append(ref).append(')')
        sb.append(" [")
        for (it in fmt)
            sb.append(it.name[0])
        sb.append("]\n")
        next?.let { sb.append(it.toString(offset + 1)) }
        return sb.toString()
    }
}