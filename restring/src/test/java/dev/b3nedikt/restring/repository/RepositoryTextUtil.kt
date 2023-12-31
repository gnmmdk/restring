package dev.b3nedikt.restring.repository

import androidx.core.text.HtmlCompat
import dev.b3nedikt.restring.PluralKeyword

internal fun generateStrings(count: Int): Map<String, String> {
    return generateSequence(seed = 0) { it + 1 }
        .take(count)
        .associate { "key$it" to "$STRING_VALUE$it" }
        .toMap()
}

internal fun generateStringArrays(count: Int): Map<String, Array<CharSequence>> {
    return generateSequence(seed = 0) { it + 1 }
        .take(count)
        .associate { "key$it" to generateStringArray() }
}

internal fun generateStringArray(): Array<CharSequence> {
    return generateSequence(seed = 0) { it + 1 }
        .take(10)
        .map { "$STRING_VALUE$it" }
        .toList()
        .toTypedArray()
}

internal fun generateTextArray(): Array<CharSequence> {
    return generateSequence { TEXT_VALUE }
        .take(10)
        .toList()
        .toTypedArray()
}

internal fun generateQuantityStrings(count: Int): Map<String, Map<PluralKeyword, CharSequence>> {
    return generateSequence(seed = 0) { it + 1 }
        .take(count)
        .associate { "key$it" to generateQuantityString() }
        .toMap()
}

internal fun generateQuantityString(): Map<PluralKeyword, CharSequence> {
    return PluralKeyword
        .values()
        .associateWith { STRING_VALUE }
}

internal fun generateQuantityText(): Map<PluralKeyword, CharSequence> {
    return PluralKeyword
        .values()
        .associateWith { TEXT_VALUE }
}

internal const val STRING_VALUE = "value"
internal val TEXT_VALUE = HtmlCompat.fromHtml("STR <b>value</b>", HtmlCompat.FROM_HTML_MODE_COMPACT)