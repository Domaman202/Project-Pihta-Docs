package ru.DmN.phtx.docs

import ru.DmN.phtx.docs.utils.Platforms
import ru.DmN.siberia.console.BaseCommands
import ru.DmN.siberia.console.BaseConsole
import ru.DmN.siberia.console.BuildCommands

object ConsoleImpl : BaseConsole() {
    @JvmStatic
    fun main(args: Array<String>) {
        run(args)
    }

    init {
        commands += BaseCommands.ALL_COMMANDS
        commands += BuildCommands.ALL_COMMANDS

        Platforms
    }
}