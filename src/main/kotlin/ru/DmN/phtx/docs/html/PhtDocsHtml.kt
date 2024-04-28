package ru.DmN.phtx.docs.html

import ru.DmN.phtx.docs.html.compilers.NCCategory
import ru.DmN.phtx.docs.html.compilers.NCInstruction
import ru.DmN.phtx.docs.html.compilers.NCModule
import ru.DmN.phtx.docs.html.utils.ctx.isDicModules
import ru.DmN.phtx.docs.html.utils.ctx.doc_modules
import ru.DmN.phtx.docs.utils.Platforms.HTML
import ru.DmN.phtx.docs.utils.node.NodeTypes.*
import ru.DmN.siberia.compiler.Compiler
import ru.DmN.siberia.compiler.ctx.CompilationContext
import ru.DmN.siberia.compiler.utils.ModuleCompilers
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

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
            if (!compiler.isDicModules) { // Провервка на то, что phtx/docs/html вообще был инициализирован в системе
                compiler.doc_modules = TreeMap()
                compiler.finalizers.add { _ ->
                    compiler.finalizers.add { it ->
                        //
                        File(it, "index.js").writeBytes(PhtDocsHtml.getModuleFile("index.js").readBytes())
                        //
                        var str = String(PhtDocsHtml.getModuleFile("index.html").readBytes())
//                    str = str.replace("<--title>", ctx.module.name)
                        str = str.replace("<--list>", StringBuilder().run {
                            append("\t<ul>\n")
                            val list = ArrayList<File>()
                            File(it).listFiles()!!.forEach { it.visit(this, list) }
                            val i = it.length
                            list.stream().map { it.path.substring(i) }.forEach {
                                append("\t\t<li><a href=\"").append(it).append("\">").append(it).append("</a></li>\n")
                            }
                            append("\t</ul>")
                            toString()
                        })
                        File(it, "index.html").writeBytes(str.toByteArray())
                    }
                }
            }
            super.load(compiler, ctx)
        }
    }

    private fun File.visit(sb: StringBuilder, list: MutableList<File>) {
        if (isDirectory)
            listFiles()!!.forEach { it.visit(sb, list) }
        else list += this
    }

    init {
        initCompilers()
    }
}