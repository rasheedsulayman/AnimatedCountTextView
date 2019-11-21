package com.r4sh33d.animatedcounttextview

import java.text.DecimalFormat
import java.text.NumberFormat

val wholeNumberDecimalFormat =
    (NumberFormat.getNumberInstance() as DecimalFormat).apply {
        applyPattern("#0")
    }

val twoDecimalPlacesFormat =
    (NumberFormat.getNumberInstance() as DecimalFormat).apply {
        applyPattern("#0.00")
    }