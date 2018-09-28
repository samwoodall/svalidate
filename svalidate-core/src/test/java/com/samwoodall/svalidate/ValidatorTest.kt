package com.samwoodall.svalidate

import android.content.Context
import android.text.Editable
import android.widget.EditText
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

class ValidatorTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()

    private val editText = mock<EditText>()
    private val context = mock<Context>()
    private val editable = mock<Editable>()

    @Before
    fun setup() {
        whenever(editText.context).thenReturn(context)
        whenever(context.getString(any())).thenReturn("")
        whenever(editText.error).thenReturn("")
        whenever(editText.text).thenReturn(editable)
    }

    @Test
    fun `success case with checkLessThan`() {
        val twoLetterExample = "ab"
        val validator = Validator().checkLessThan(3)
        whenever(editable.toString()).thenReturn(twoLetterExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkLessThan`() {
        val threeLetterExample = "aba"
        val validator = Validator().checkLessThan(3)
        whenever(editable.toString()).thenReturn(threeLetterExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkNotBlank`() {
        val notBlankExample = "ab"
        val validator = Validator().checkNotBlank()
        whenever(editable.toString()).thenReturn(notBlankExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkNotBlank`() {
        val blankExample = ""
        val validator = Validator().checkNotBlank()
        whenever(editable.toString()).thenReturn(blankExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkGreaterThan`() {
        val threeLetterExample = "aba"
        val validator = Validator().checkGreaterThan(2)
        whenever(editable.toString()).thenReturn(threeLetterExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkGreaterThan`() {
        val twoLetterExample = "ab"
        val validator = Validator().checkGreaterThan(2)
        whenever(editable.toString()).thenReturn(twoLetterExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkAllLower`() {
        val allLowerExample = "aa"
        val validator = Validator().checkAllLower()
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkAllLower`() {
        val startsWithUpperThenLowerExample = "Aa"
        val validator = Validator().checkAllLower()
        whenever(editable.toString()).thenReturn(startsWithUpperThenLowerExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkAllUpper`() {
        val allUpperExample = "AA"
        val validator = Validator().checkAllUpper()
        whenever(editable.toString()).thenReturn(allUpperExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkAllUpper`() {
        val startsWithUpperThenLowerExample = "Aa"
        val validator = Validator().checkAllUpper()
        whenever(editable.toString()).thenReturn(startsWithUpperThenLowerExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkAtLeastOneLower`() {
        val startsWithUpperThenLowerExample = "Aa"
        val validator = Validator().checkAtLeastOneLower()
        whenever(editable.toString()).thenReturn(startsWithUpperThenLowerExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkAtLeastOneLower`() {
        val allUpperExample = "AA"
        val validator = Validator().checkAtLeastOneLower()
        whenever(editable.toString()).thenReturn(allUpperExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkAtLeastOneUpper`() {
        val allUpperExample = "AA"
        val validator = Validator().checkAtLeastOneUpper()
        whenever(editable.toString()).thenReturn(allUpperExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkAtLeastOneUpper`() {
        val allLowerExample = "aa"
        val validator = Validator().checkAtLeastOneUpper()
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkAtLeastOneDigit`() {
        val oneDigitExample = "sdsd6"
        val validator = Validator().checkAtLeastOneDigit()
        whenever(editable.toString()).thenReturn(oneDigitExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkAtLeastOneDigit`() {
        val allLowerExample = "aa"
        val validator = Validator().checkAtLeastOneDigit()
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkAtLeastOneLetter`() {
        val oneLetterExample = "s234"
        val validator = Validator().checkAtLeastOneLetter()
        whenever(editable.toString()).thenReturn(oneLetterExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkAtLeastOneLetter`() {
        val allDigitsExample = "33"
        val validator = Validator().checkAtLeastOneLetter()
        whenever(editable.toString()).thenReturn(allDigitsExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkStartsWithUpper`() {
        val startsWithUpperThenLowerExample = "Aa"
        val validator = Validator().checkStartsWithUpper()
        whenever(editable.toString()).thenReturn(startsWithUpperThenLowerExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkStartsWithUpper`() {
        val allLowerExample = "aa"
        val validator = Validator().checkStartsWithUpper()
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkAllLetters`() {
        val startsWithUpperThenLowerExample = "Aa"
        val validator = Validator().checkAllLetters()
        whenever(editable.toString()).thenReturn(startsWithUpperThenLowerExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkAllLetters`() {
        val oneLetterExample = "s234"
        val validator = Validator().checkAllLetters()
        whenever(editable.toString()).thenReturn(oneLetterExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkAllNumbers`() {
        val allDigitsExample = "33"
        val validator = Validator().checkAllNumbers()
        whenever(editable.toString()).thenReturn(allDigitsExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkAllNumbers`() {
        val oneLetterExample = "s234"
        val validator = Validator().checkAllNumbers()
        whenever(editable.toString()).thenReturn(oneLetterExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkAllAlphanumeric`() {
        val oneLetterExample = "s234"
        val validator = Validator().checkAllAlphanumeric()
        whenever(editable.toString()).thenReturn(oneLetterExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkAllAlphanumeric`() {
        val oneSpecialCharExample = "s234_"
        val validator = Validator().checkAllAlphanumeric()
        whenever(editable.toString()).thenReturn(oneSpecialCharExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkNoLetters`() {
        val allDigitsExample = "33"
        val validator = Validator().checkNoLetters()
        whenever(editable.toString()).thenReturn(allDigitsExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkNoLetters`() {
        val oneSpecialCharExample = "s234_"
        val validator = Validator().checkNoLetters()
        whenever(editable.toString()).thenReturn(oneSpecialCharExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkNoNumbers`() {
        val allLowerExample = "aa"
        val validator = Validator().checkNoNumbers()
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkNoNumbers`() {
        val oneSpecialCharExample = "s234_"
        val validator = Validator().checkNoNumbers()
        whenever(editable.toString()).thenReturn(oneSpecialCharExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkDoesNotContain`() {
        val allLowerExample = "aa"
        val validator = Validator().checkDoesNotContain("b")
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkDoesNotContain`() {
        val allLowerExample = "aa"
        val validator = Validator().checkDoesNotContain("a")
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkContains`() {
        val allLowerExample = "aa"
        val validator = Validator().checkContains("a")
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkContains`() {
        val allLowerExample = "aa"
        val validator = Validator().checkContains("b")
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkContainsAtMostOne`() {
        val containtsOneExample = "aab"
        val validator = Validator().checkContainsAtMostOne('b')
        whenever(editable.toString()).thenReturn(containtsOneExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkContainsAtMostOne`() {
        val allLowerExample = "aa"
        val validator = Validator().checkContainsAtMostOne('a')
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkEndsWith`() {
        val endsWithExample = "aab"
        val validator = Validator().checkEndsWith("ab")
        whenever(editable.toString()).thenReturn(endsWithExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkEndsWith`() {
        val allLowerExample = "aa"
        val validator = Validator().checkEndsWith("b")
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkStartsWith`() {
        val endsWithExample = "aab"
        val validator = Validator().checkStartWith("aa")
        whenever(editable.toString()).thenReturn(endsWithExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkStartsWith`() {
        val allLowerExample = "aa"
        val validator = Validator().checkStartWith("b")
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkForStandardPassword`() {
        val standardPasswordExample = "Jojo_123"
        val validator = Validator().checkForStandardPassword()
        whenever(editable.toString()).thenReturn(standardPasswordExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkForStandardPassword`() {
        val allLowerExample = "aa"
        val validator = Validator().checkForStandardPassword()
        whenever(editable.toString()).thenReturn(allLowerExample)

        assertFalse(validator.validate(editText))
    }

    @Test
    fun `success case with checkMatchesRegex`() {
        val standardPasswordExample = "Jojo_1-23"
        val validator = Validator().checkMatchesRegex(Regex("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=_]).*\$"))
        whenever(editable.toString()).thenReturn(standardPasswordExample)

        assertTrue(validator.validate(editText))
    }

    @Test
    fun `failure case with checkMatchesRegex`() {
        val nonStandardPasswordExample = "jojo123"
        val validator = Validator().checkMatchesRegex(Regex("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=_]).*\$"))
        whenever(editable.toString()).thenReturn(nonStandardPasswordExample)

        assertFalse(validator.validate(editText))
    }
}
