package com.sailxx.ember

import android.content.Context
import android.content.res.Configuration
import androidx.core.content.edit
import java.util.Locale

internal enum class EmberLanguage(val tag: String, val label: String) {
    English("en", "English"),
    Russian("ru", "Русский");

    fun applyTo(context: Context): Context {
        val locale = Locale.forLanguageTag(tag)
        val configuration = Configuration(context.resources.configuration).apply { setLocale(locale) }
        return context.createConfigurationContext(configuration)
    }

    fun save(context: Context) {
        Prefs.sp(context).edit { putString(KEY, tag) }
    }

    companion object {
        private const val KEY = "language"

        fun load(context: Context): EmberLanguage {
            val tag = Prefs.sp(context).getString(KEY, null)
            return entries.firstOrNull { it.tag == tag } ?: English
        }
    }
}
