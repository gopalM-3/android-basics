package com.apollo.tiptime

import org.junit.Assert
import org.junit.Test
import java.text.NumberFormat
import java.util.Locale

class TipCalculatorTests {

    @Test
    fun calculateTip_20PercentNoRoundUp() {
        val amount: Double = 10.0
        val tip: Double = 20.0
        val expectedTip = NumberFormat
            .getCurrencyInstance(Locale("en", "IN"))
            .format(2)
        val actualTip = calculateTip(amount, tip, false)
        Assert.assertEquals(expectedTip, actualTip)
    }

}