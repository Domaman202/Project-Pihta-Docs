package ru.DmN.phtx.docs.html.compilers

import ru.DmN.phtx.docs.ast.NodeInstruction
import ru.DmN.phtx.docs.html.PhtDocsHtml
import ru.DmN.phtx.docs.html.utils.ctx.doc_module
import ru.DmN.phtx.docs.html.utils.ctx.instructions
import ru.DmN.phtx.docs.html.utils.ctx.modules
import ru.DmN.phtx.docs.html.utils.format.formatToHTML
import ru.DmN.siberia.compiler.Compiler
import ru.DmN.siberia.compiler.ctx.CompilationContext
import ru.DmN.siberia.compilers.INodeCompiler
import java.io.File

object NCInstruction : INodeCompiler<NodeInstruction> {
    override fun compile(node: NodeInstruction, compiler: Compiler, ctx: CompilationContext) {
        ctx.instructions += Triple(node.long!!, node.short, node.symbol)
        compiler.finalizers.add { it ->
            var str = String(PhtDocsHtml.getModuleFile("instruction.html").readBytes())
            str = str.replace(
                "<--sidebar>",
                StringBuilder().apply {
                    var i = 0
                    compiler.modules.forEach { (module, categories) ->
                        if (i++ > 0)
                            append('\n')
                        append("\t\t<div class=\"module\"><div class=\"caption\">").append(module).append("</div>\n")
                        var j = 0
                        categories.forEach { (category, instructions) ->
                            if (j++ > 0)
                                append('\n')
                            append("\t\t\t<div class=\"table\"><div class=\"caption\">").append(category).append("</div><br><div class=\"body\">\n")
                            val left = StringBuilder().append("\t\t\t\t<div class=\"left\">\n")
                            val right = StringBuilder().append("\t\t\t\t<div class=\"right\">\n")
                            instructions.forEach {
                                if (it.second != null) {
                                    left.appendTable(module, it.second!!, it.first)
                                    right.appendTable(module, it.first, it.first)
                                } else if (it.third != null) {
                                    left.appendTable(module, it.first, it.first)
                                    right.appendTable(module, it.third!!, it.first)
                                } else {
                                    left.appendTable(module, it.first, it.first)
                                    right.append("\t\t\t\t\t<br>\n")
                                }
                            }
                            append(left.append("\t\t\t\t</div>\n"))
                            append(right.append("\t\t\t\t</div>\n"))
                            append("\t\t\t</div></div>")
                        }
                        append("\t\t</div>")
                    }
                }.toString()
            )
            str = str.replace(
                "<--names>",
                StringBuilder().apply {
                    append("\t\t\t")
                    node.long?.let { append("<div class=\"name-parent\"><p class=\"border-margin\">").append(it).append("</p></div>") }
                    node.short?.let { append("<div class=\"name-parent\"><p class=\"border-margin\">").append(it).append("</p></div>") }
                    node.symbol?.let { append("<div class=\"name-parent\"><p class=\"border-margin\">").append(it).append("</p></div>") }
                }.toString()
            )
            str = str.replace(
                "<--description>",
                formatToHTML(node.desc!!, "class=\"border-margin\"")
            )
            str = str.replace(
                "<--usages>",
                StringBuilder().apply {
                    node.usage.forEachIndexed { i, it1 ->
                        if (i > 0)
                            append('\n')
                        append("\t\t<div class=\"usage\"><div class=\"code\"><code>").append(it1.code).append("</code></div>\n")
                        it1.arguments.forEach { it2 ->
                            append("\t\t\t<div class=\"usage-arg\">\n")
                            it2.names.forEach {
                                append("\t\t\t\t<div class=\"usage-arg-name\"><p class=\"border-margin\">").append(it).append("</p></div>\n")
                            }
                            append("\t\t\t\t<div class=\"usage-arg-desc\">").append(formatToHTML("- " + it2.desc, "class=\"border-margin\"")).append("</div>\n\t\t\t</div>\n")
                        }
                        append("\t\t</div>")
                    }
                }.toString()
            )
            str = str.replace(
                "<--examples>",
                StringBuilder().apply {
                    node.examples.forEachIndexed { i, it1 ->
                        if (i > 0)
                            append('\n')
                        append("\t\t<div class=\"example\">\n\t\t\t<pre class=\"code\">\n").append(it1.code).append("\t\t\t</pre>\n")
                        it1.desc?.let { append("\t\t\t").append(formatToHTML(it, "class=\"border-margin\"")).append('\n') }
                        append("\t\t</div>")
                    }
                }.toString()
            )
            File("$it/${ctx.doc_module}/instructions/${node.long()}.html").writeText(str)
        }
    }

    private fun StringBuilder.appendTable(module: String, name: String, ref: String) {
        append("\t\t\t\t\t<a href=\"/").append(module).append("/instruction/").append(ref).append("\">").append(name).append("</a><br>\n")
    }
}