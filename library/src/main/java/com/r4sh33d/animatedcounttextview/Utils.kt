package com.r4sh33d.animatedcounttextview

import java.text.DecimalFormat
import java.text.NumberFormat

interface CountEndListener {
    fun onCountFinish()
}

sealed class NumberType(val formatter: DecimalFormat) {
    class Integer(format: DecimalFormat = wholeNumberDecimalFormat) : NumberType(format)
    class Decimal(format: DecimalFormat = twoDecimalPlacesFormat) : NumberType(format)
}

val wholeNumberDecimalFormat =
    (NumberFormat.getNumberInstance() as DecimalFormat).apply {
        applyPattern("#0")
    }

val twoDecimalPlacesFormat =
    (NumberFormat.getNumberInstance() as DecimalFormat).apply {
        applyPattern("#0.00")
    }