package ru.DmN.phtx.docs.utils

import ru.DmN.siberia.utils.IPlatform
import ru.DmN.siberia.utils.vtype.TypesProvider
import ru.DmN.siberia.utils.vtype.TypesProvider.VoidTypesProvider

/**
 * Стандартные целевые платформы.
 */
enum class Platforms : IPlatform {
    HTML;

    companion object {
        init {
            IPlatform.PLATFORMS += HTML
            TypesProvider.add(HTML, VoidTypesProvider())
        }
    }
}