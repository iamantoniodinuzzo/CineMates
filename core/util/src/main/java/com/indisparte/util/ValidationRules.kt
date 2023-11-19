package com.indisparte.util

/**
 *@author Antonio Di Nuzzo
 */
// TODO: String resource
val notEmptyValidationRule = Validator.ValidationRule("Il campo non può essere vuoto") {
    it.isNotEmpty()
}

// TODO: String resource
val nameValidationRule = listOf(
    Validator.ValidationRule("Non puoi inserire simboli o numeri") {
        val regex = Regex("^[a-zA-Z'\\s]*$")
        regex.matches(it)
    },
    notEmptyValidationRule
)
