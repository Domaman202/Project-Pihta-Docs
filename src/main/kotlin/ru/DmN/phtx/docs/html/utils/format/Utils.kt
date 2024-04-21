package ru.DmN.phtx.docs.html.utils.format

import ru.DmN.phtx.docs.utils.format.FmtRefString
import ru.DmN.phtx.docs.utils.format.FmtString
import ru.DmN.phtx.docs.utils.format.FmtString.Formatting.*
import ru.DmN.phtx.docs.utils.format.format

fun String.normalize(): String =
    this.replace(Regex("[$@]"), "")

fun formatToHTML(input: String, meta: String? = null): String = StringBuilder().run {
    append("<p")
    meta?.let { append(' ').append(it) }
    append('>')
    formatToHTML(format(input))
    append("</p>")
    toString()
}

private fun StringBuilder.formatToHTML(str: FmtString) {
    str.fmt.forEach {
        append(
            when (it) {
                BOLD      -> "<b>"
                ITALIC    -> "<i>"
                REFERENCE -> "<a href=\"${(str as FmtRefString).ref}\">"
            }
        )
    }
    append(str.text.replace("\n", "<br>"))
    str.fmt.forEach {
        append(
            when (it) {
                BOLD      -> "</b>"
                ITALIC    -> "</i>"
                REFERENCE -> "</a>"
            }
        )
    }
    str.next?.let { formatToHTML(it) }
}