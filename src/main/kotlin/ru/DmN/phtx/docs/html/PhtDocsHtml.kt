package ru.DmN.phtx.docs.html

import ru.DmN.phtx.docs.html.compilers.NCCategory
import ru.DmN.phtx.docs.html.compilers.NCInstruction
import ru.DmN.phtx.docs.html.compilers.NCModule
import ru.DmN.phtx.docs.html.utils.ctx.isModules
import ru.DmN.phtx.docs.html.utils.ctx.modules
import ru.DmN.phtx.docs.utils.Platforms.HTML
import ru.DmN.phtx.docs.utils.node.NodeTypes.*
import ru.DmN.siberia.compiler.Compiler
import ru.DmN.siberia.compiler.ctx.CompilationContext
import ru.DmN.siberia.compiler.utils.ModuleCompilers
import java.io.File
import java.util.*

object PhtDocsHtml : ModuleCompilers("phtx/docs/html", HTML) {
    private fun initCompilers() {
        // c
        add(CATEGORY_, NCCategory)
        // i
        add(INSTR_,    NCInstruction)
        // m
        add(MODULE_,   NCModule)
    }

    override fun load(compiler: Compiler, ctx: CompilationContext) {
        if (!ctx.loadedModules.contains(this)) {
            if (!compiler.isModules) { // Провервка на то, что phtx/docs/html вообще был инициализирован в системе
                compiler.modules = TreeMap()
                compiler.finalizers.add {
                    File(it, "index.js").writeBytes(PhtDocsHtml.getModuleFile("index.js").readBytes())
                    //
                    var str = String(PhtDocsHtml.getModuleFile("index.html").readBytes())
//                    str = str.replace("<--title>", ctx.module.name)
                    str = str.replace("<--list>", StringBuilder().run {
                        append("\t<ul>\n")
                        File(it).visit(this, "")
                        append("\t</ul>")
                        toString()
                    })
                    File(it, "index.html").writeBytes(str.toByteArray())
                }
            }
            super.load(compiler, ctx)
        }
    }

    private fun File.visit(sb: StringBuilder, dir: String) {
        if (isDirectory) {
            listFiles()!!.forEach { it.visit(sb, "$dir/$name") }
        } else if (name.endsWith(".html")) {
            sb.append("\t\t<li><a href=\"").append(dir).append('/').append(name).append("\">").append(dir).append('/').append(name).append("</a></li>\n")
        }
    }

    init {
        initCompilers()
    }
}