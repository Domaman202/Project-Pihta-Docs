@file:Suppress("UNCHECKED_CAST")
package ru.DmN.phtx.docs.html.utils.ctx

import ru.DmN.phtx.docs.html.utils.ctx.ContextKeys.CATEGORIES
import ru.DmN.phtx.docs.html.utils.ctx.ContextKeys.INSTRUCTIONS
import ru.DmN.siberia.compiler.Compiler
import ru.DmN.siberia.utils.ctx.IContextCollection

val Compiler.isCategories
    get() = this.contexts.containsKey(CATEGORIES)

var Compiler.categories
    set(value) { this.contexts[CATEGORIES] = value }
    get() = this.contexts[CATEGORIES] as MutableMap<String, MutableList<Triple<String, String?, String?>>>

val IContextCollection<*>.isInstructions
    get() = this.contexts.containsKey(INSTRUCTIONS)

var IContextCollection<*>.instructions
    set(value) { this.contexts[INSTRUCTIONS] = value }
    get() = this.contexts[INSTRUCTIONS] as MutableList<Triple<String, String?, String?>>