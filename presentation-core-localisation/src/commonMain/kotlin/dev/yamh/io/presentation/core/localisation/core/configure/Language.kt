package dev.yamh.io.presentation.core.localisation.core.configure

import dev.yamh.io.presentation.core.localisation.source.Localisation
import dev.yamh.io.presentation.core.localisation.source.locale.localeEn
import dev.yamh.io.presentation.core.localisation.source.locale.localeUk

public enum class Language(public val iso: String) {
    English(iso = "en"),
    Ukrainian(iso = "uk")
}

public fun getLocalisation(language: Language): Localisation {
    return when(language){
        Language.English -> localeEn
        Language.Ukrainian -> localeUk
    }
}
