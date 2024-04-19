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
                    compiler.categories.forEach { (k, v) ->
                        append("<div class=\"table\"><table>\n<caption>").append(k).append("</caption>")
                        v.forEach {
                            append("\n<tr>")
                            if (it.second != null) {
                                append("<td><a class=\"short-name\" href=\"/").append(it.first).append("\">").append(it.second).append("</a></td>")
                                append("<td><a class=\"long-name\" href=\"/").append(it.first).append("\">").append(it.first).append("</a></td>")
                            } else if (it.third != null) {
                                append("<td><a class=\"long-name\" href=\"/").append(it.first).append("\">").append(it.first).append("</a></td>")
                                append("<td><a class=\"symbol-name\" href=\"/").append(it.first).append("\">").append(it.third).append("</a></td>")
                            } else append("<td><a class=\"long-name\" href=\"/").append(it.first).append("\">").append(it.first).append("</a></td>")
                            append("</tr>")
                        }
                        append("\n</table></div>\n")
                    }
                }.toString()
            )
            str = str.replace(
                "<--names>",
                StringBuilder().apply {
                    node.long?.let { append("\n<div class=\"name-parent\"><div class=\"border-margin\">").append(it).append("</div></div>") }
                    node.short?.let { append("\n<div class=\"name-parent\"><div class=\"border-margin\">").append(it).append("</div></div>") }
                    node.symbol?.let { append("\n<div class=\"name-parent\"><div class=\"border-margin\">").append(it).append("</div></div>") }
                }.toString()
            )
            str = str.replace(
                "<--description>",
                node.desc ?: ""
            )
            str = str.replace(
                "<--usages>",
                StringBuilder().apply {
                    node.usage.forEach { it1 ->
                        append("<div class=\"usage\">\n<div class=\"code\"><code>\n").append(it1.code).append("</code></div>\n")
                        it1.arguments.forEach { it2 ->
                            append("<div class=\"usage-arg\">\n")
                            it2.names.forEach {
                                append("<div class=\"usage-arg-name\"><div class=\"border-margin\">").append(it).append("</div></div>\n")
                            }
                            append("<div class=\"usage-arg-desc\"><div class=\"border-margin\">- ").append(it2.desc).append("</div></div>\n").append("</div>\n")
                        }
                        append("</div>\n")
                    }
                }.toString()
            )
            str = str.replace(
                "<--examples>",
                StringBuilder().apply {
                    node.examples.forEach { it1 ->
                        append("\n<div class=\"example\">\n<pre class=\"code\">\n").append(it1.code).append("</pre>")
                        it1.desc?.let { append("\n<div class=\"border-margin\">").append(it).append("</div>") }
                        append("\n</div>")
                    }
                }.toString()
            )
            File("$it/${node.long()}.html").writeText(str)
        }
    }
}