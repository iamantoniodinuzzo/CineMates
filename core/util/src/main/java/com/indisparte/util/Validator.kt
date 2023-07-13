package com.indisparte.util

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.viewbinding.ViewBinding
import com.google.android.material.textfield.TextInputLayout


/**
 * A generic validator class for performing validation on views using ViewBinding.
 *
 * @param binding The ViewBinding instance associated with the views to be validated.
 * @param T The type of the ViewBinding class.
 * @author Antonio Di Nuzzo (Indisparte)
 * @see [Simplifying Input Form Validations with Generic Coding in Android](https://kashifahmad.medium.com/simplifying-input-form-validations-with-generic-coding-in-android-3e3f6db76d68)
 */
class Validator<T : ViewBinding>(private val binding: T) {

    /**
     * Checks the validity of the views associated with the provided ViewBinding.
     *
     * @return `true` if all the views pass the validation; `false` otherwise.
     */
    fun isValid(): Boolean {
        var isValid = true
        val root = binding.root

        if (root is ViewGroup) {
            val allChildren = root.getAllChildren()
            for (view in allChildren) {
                if (view is EditText) {
                    val rules =
                        view.getTag(R.id.validation_rules) as? List<ValidationRule> ?: continue
                    for (rule in rules) {
                        val textInputLayout = view.findParentOfType<TextInputLayout>()
                        if (!rule.validate(view.text.toString())) {
                            textInputLayout?.error = rule.errorMessage
                            isValid = false
                        }
                    }

                }
            }
        }
        return isValid
    }

    /**
     * A data class representing a validation rule for a specific EditText view.
     *
     * @param errorMessage The error message to be displayed if the validation fails.
     * @param validator A function that takes an input string and returns `true` if the validation passes; `false` otherwise.
     */
    data class ValidationRule(val errorMessage: String, val validator: (String) -> Boolean) {
        /**
         * Validates the input string based on the provided validation rule.
         *
         * @param input The input string to be validated.
         * @return `true` if the input passes the validation; `false` otherwise.
         */
        fun validate(input: String) = validator(input)
    }
}

private inline fun <reified T : View> View.findParentOfType(): T? {
    var parent = parent
    while (parent != null) {
        if (parent is T) {
            return parent
        }
        parent = parent.parent
    }
    return null
}

private fun View.getAllChildren(): List<View> {
    val result = ArrayList<View>()
    if (this !is ViewGroup) {
        result.add(this)
    } else {
        for (index in 0 until this.childCount) {
            val child = this.getChildAt(index)
            result.addAll(child.getAllChildren())
        }
    }
    return result
}


