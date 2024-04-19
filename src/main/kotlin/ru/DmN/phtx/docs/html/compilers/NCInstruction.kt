package ru.DmN.phtx.docs.html.compilers

import ru.DmN.phtx.docs.ast.NodeInstruction
import ru.DmN.phtx.docs.html.PhtDocsHtml
import ru.DmN.phtx.docs.html.utils.ctx.categories
import ru.DmN.phtx.docs.html.utils.ctx.instructions
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
                    compiler.categories.forEach { (k, v) ->
                        if (i++ > 0)
                            append('\n')
                        append("\t\t<div class=\"table\"><div class=\"caption\">").append(k).append("</div><br><div class=\"body\">\n")
                        val left = StringBuilder().append("\t\t<div class=\"left\">\n")
                        val right = StringBuilder().append("\t\t<div class=\"right\">\n")
                        v.forEach {
                            if (it.second != null) {
                                left.append("\t\t\t\t<a href=\"/").append(it.first).append("\">").append(it.second).append("</a><br>\n")
                                right.append("\t\t\t\t<a href=\"/").append(it.first).append("\">").append(it.first).append("</a><br>\n")
                            } else if (it.third != null) {
                                left.append("\t\t\t\t<a href=\"/").append(it.first).append("\">").append(it.first).append("</a><br>\n")
                                right.append("\t\t\t\t<a href=\"/").append(it.first).append("\">").append(it.third).append("</a><br>\n")
                            } else {
                                left.append("\t\t\t\t<a href=\"/").append(it.first).append("\">").append(it.first).append("</a><br>\n")
                                right.append("\t\t\t\t<br>\n")
                            }
                        }
                        append(left.append("\t\t\t</div>\n"))
                        append(right.append("\t\t\t</div>\n"))
                        append("\t\t</div></div>")
                    }
                }.toString()
            )
            str = str.replace(
                "<--names>",
                StringBuilder().apply {
                    append("\t\t\t")
                    node.long?.let { append("<div class=\"name-parent\"><div class=\"border-margin\">").append(it).append("</div></div>") }
                    node.short?.let { append("<div class=\"name-parent\"><div class=\"border-margin\">").append(it).append("</div></div>") }
                    node.symbol?.let { append("<div class=\"name-parent\"><div class=\"border-margin\">").append(it).append("</div></div>") }
                }.toString()
            )
            str = str.replace(
                "<--description>",
                node.desc ?: ""
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
                                append("\t\t\t\t<div class=\"usage-arg-name\"><div class=\"border-margin\">").append(it).append("</div></div>\n")
                            }
                            append("\t\t\t\t<div class=\"usage-arg-desc\"><div class=\"border-margin\">- ").append(it2.desc).append("</div></div>\n\t\t\t</div>\n")
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
                        it1.desc?.let { append("\t\t\t<div class=\"border-margin\">").append(it).append("</div>\n") }
                        append("\t\t</div>")
                    }
                }.toString()
            )
            File("$it/${node.long()}.html").writeText(str)
        }
    }
}