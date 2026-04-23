package com.ucb.primerproyecto.translation

object LocalTranslationService {

    private val translations = mutableMapOf<String, Map<String, String>>()

    fun load(locale: String, strings: Map<String, String>) {
        translations[locale] = strings
    }

    // clave para el locale actual
    fun get(key: String, locale: String): String {
        return translations[locale]?.get(key)
            ?: translations["en"]?.get(key)
            ?: key
    }
}