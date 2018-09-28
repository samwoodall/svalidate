package com.samwoodall.svalidate

import android.support.annotation.StringRes
import android.widget.EditText

private typealias ValidationRule = (String) -> ErrorMessage?

private data class ErrorMessage(val message: Int, val arg: String?)

data class ValidationResult(val success: Boolean, val errorMessage: Int?)

class Validator {
    private var rules = mutableListOf<ValidationRule>()

    fun validate(editText: EditText, showError: Boolean = true) = validateWithErrorMessage(editText, showError).success

    fun validateWithErrorMessage(editText: EditText, showError: Boolean = true): ValidationResult {
        val errorMessage = checkRules(editText.text.toString())

        if (errorMessage != null && showError) {
            editText.error = editText.context.getString(
                if (errorMessage.arg == null) errorMessage.message else errorMessage.message,
                errorMessage.arg
            )
        }

        return ValidationResult(errorMessage == null, errorMessage?.message)
    }

    private fun checkRules(text: String): ErrorMessage? = rules.map { it(text) }.find { it != null }

    private fun addRule(validationRule: ValidationRule) = apply { rules.add(validationRule) }

    private fun resolveRule(ruleCase: Boolean, errorMessage: Int, arg: String? = null): ErrorMessage? =
        if (ruleCase) null else ErrorMessage(errorMessage, arg)

    fun checkLessThan(length: Int, @StringRes errorMessage: Int = R.string.svalidate_less_than) =
        addRule { resolveRule(it.length < length, errorMessage, length.toString()) }

    fun checkNotBlank(@StringRes errorMessage: Int = R.string.svalidate_not_blank) =
        addRule { resolveRule(it.isNotBlank(), errorMessage) }

    fun checkGreaterThan(length: Int, @StringRes errorMessage: Int = R.string.svalidate_greater_than) =
        addRule { resolveRule(it.length > length, errorMessage, length.toString()) }

    fun checkAllLower(@StringRes errorMessage: Int = R.string.svalidate_lower_case) =
        addRule { resolveRule(it == it.toLowerCase(), errorMessage) }

    fun checkAllUpper(@StringRes errorMessage: Int = R.string.svalidate_upper_case) =
        addRule { resolveRule(it == it.toUpperCase(), errorMessage) }

    fun checkAtLeastOneLower(@StringRes errorMessage: Int = R.string.svalidate_one_lower_case) =
        addRule { resolveRule(it.any { char -> char.toLowerCase() == char }, errorMessage) }

    fun checkAtLeastOneUpper(@StringRes errorMessage: Int = R.string.svalidate_one_upper_case) =
        addRule { resolveRule(it.any { char -> char.toUpperCase() == char }, errorMessage) }

    fun checkAtLeastOneDigit(@StringRes errorMessage: Int = R.string.svalidate_one_number) =
        addRule { resolveRule(it.any { char -> char.isDigit() }, errorMessage) }

    fun checkAtLeastOneLetter(@StringRes errorMessage: Int = R.string.svalidate_one_letter) =
        addRule { resolveRule(it.any { char -> char.isLetter() }, errorMessage) }

    fun checkStartsWithUpper(@StringRes errorMessage: Int = R.string.svalidate_start_with_upper) =
        addRule { resolveRule(it[0].isLetter() && it.toUpperCase()[0] == it[0], errorMessage) }

    fun checkAllLetters(@StringRes errorMessage: Int = R.string.svalidate_all_letters) =
        addRule { resolveRule(!it.any { char -> !char.isLetter() }, errorMessage) }

    fun checkAllNumbers(@StringRes errorMessage: Int = R.string.svalidate_all_numbers) =
        addRule { resolveRule(!it.any { char -> !char.isDigit() }, errorMessage) }

    fun checkAllAlphanumeric(@StringRes errorMessage: Int = R.string.svalidate_all_alphanumeric) =
        addRule { resolveRule(!it.any { char -> !char.isLetterOrDigit() }, errorMessage) }

    fun checkNoLetters(@StringRes errorMessage: Int = R.string.svalidate_no_letters) =
        addRule { resolveRule(!it.any { char -> char.isLetter() }, errorMessage) }

    fun checkNoNumbers(@StringRes errorMessage: Int = R.string.svalidate_no_numbers) =
        addRule { resolveRule(!it.any { char -> char.isDigit() }, errorMessage) }

    fun checkDoesNotContain(string: String, @StringRes errorMessage: Int = R.string.svalidate_not_contain) =
        addRule { resolveRule(!it.contains(string), errorMessage) }

    fun checkContains(string: String, @StringRes errorMessage: Int = R.string.svalidate_contains) =
        addRule { resolveRule(it.contains(string), errorMessage) }

    fun checkContainsAtMostOne(char: Char, @StringRes errorMessage: Int = R.string.svalidate_contains_at_most_one) =
        addRule { resolveRule(it.filter { c -> c == char }.length <= 1, errorMessage, char.toString()) }

    fun checkEndsWith(string: String, @StringRes errorMessage: Int = R.string.svalidate_ends_with) =
        addRule { resolveRule(it.endsWith(string), errorMessage, string) }

    fun checkStartWith(string: String, @StringRes errorMessage: Int = R.string.svalidate_starts_with) =
        addRule { resolveRule(it.startsWith(string), errorMessage, string) }

    //At least 8 chars with at least one upper, one lower and one special char
    fun checkForStandardPassword(@StringRes errorMessage: Int = R.string.svalidate_standard_password) = addRule {
        resolveRule(it.matches(Regex("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=_]).*\$")), errorMessage)
    }

    fun checkMatchesRegex(regex: Regex, errorMessage: Int = R.string.svalidate_regex) =
        addRule { resolveRule(it.matches(regex), errorMessage) }
}
