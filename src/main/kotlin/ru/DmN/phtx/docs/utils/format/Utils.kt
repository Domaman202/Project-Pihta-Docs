package ru.DmN.phtx.docs.utils.format

import ru.DmN.phtx.docs.utils.format.FmtString.Formatting
import ru.DmN.phtx.docs.utils.format.FmtString.Formatting.*

fun format(input: String, ps: Int = 0): FmtString {
    val start = input.indexOf('@', ps)
    if (start == -1)
        return FmtString(input.substring(ps), emptyList(), null)
    val fmt = ArrayList<Formatting>()
    val stop = input.indexOf('}', start)
    var i = start
    while (true) {
        fmt += when (input[++i]) {
            'b' -> BOLD
            'i' -> ITALIC
            'r' -> REFERENCE
            '{',
            '(' -> break

            else -> throw RuntimeException()
        }
    }
    return if (fmt.contains(REFERENCE)) {
        val k = input.indexOf(')')
        FmtString(
            input.substring(0, start),
            emptyList(),
            FmtRefString(
                input.substring(input.indexOf('{', k) + 1, stop),
                fmt,
                format(input, stop + 1),
                input.substring(i + 1, k)
            )
        )
    } else {
        FmtString(
            input.substring(ps, start),
            emptyList(),
            FmtString(
                input.substring(i + 1, stop),
                fmt,
                format(input, stop + 1)
            )
        )
    }
}