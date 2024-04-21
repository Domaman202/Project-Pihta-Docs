@file:Suppress("UNCHECKED_CAST")
package ru.DmN.phtx.docs.html.utils.ctx

import ru.DmN.phtx.docs.html.utils.ctx.ContextKeys.*
import ru.DmN.siberia.compiler.Compiler
import ru.DmN.siberia.utils.ctx.IContextCollection

var IContextCollection<*>.doc_module
    set(value) { this.contexts[DOC_MODULE] = value }
    get() = this.contexts[DOC_MODULE] as String

val Compiler.isModules
    get() = this.contexts.containsKey(MODULES)

var Compiler.modules
    set(value) { this.contexts[MODULES] = value }
    get() = this.contexts[MODULES] as MutableMap<String, MutableMap<String, MutableList<Triple<String, String?, String?>>>>

val IContextCollection<*>.isCategories
    get() = this.contexts.containsKey(CATEGORIES)

var IContextCollection<*>.categories
    set(value) { this.contexts[CATEGORIES] = value }
    get() = this.contexts[CATEGORIES] as MutableMap<String, MutableList<Triple<String, String?, String?>>>

val IContextCollection<*>.isInstructions
    get() = this.contexts.containsKey(INSTRUCTIONS)

var IContextCollection<*>.instructions
    set(value) { this.contexts[INSTRUCTIONS] = value }
    get() = this.contexts[INSTRUCTIONS] as MutableList<Triple<String, String?, String?>>