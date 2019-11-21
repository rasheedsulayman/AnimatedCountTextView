package com.r4sh33d.animatedcounttextview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.appcompat.widget.AppCompatTextView
import com.r4sh33d.animatedcounttextview.NumberType.Integer
import java.text.DecimalFormat

class AnimatedCountTextView(context: Context, attrs: AttributeSet?) :
    AppCompatTextView(context, attrs) {
    private lateinit var valueAnimator: ValueAnimator
    private var numberType: NumberType = Integer()
    private var startValue: Number = 0f
    private var endValue: Number = 0f
    private var animationDuration: Long? = null
    private var animationEndListener: AnimationEndListener? = null
    private var interpolator: Interpolator = AccelerateDecelerateInterpolator()

    fun startWith(value: Number) {
        startValue = value
    }

    fun duration(duration: Long) {
        animationDuration = duration
    }

    fun endWith(value: Number) {
        endValue = value
    }

    fun numberType(numberType: NumberType) {
        this.numberType = numberType
    }

    fun interpolator(interpolator: Interpolator) {
        this.interpolator = interpolator
    }

    fun play() {
        if (::valueAnimator.isInitialized && valueAnimator.isRunning) {
            valueAnimator.end()
        }
        valueAnimator = if (numberType is Integer) {
            ValueAnimator.ofInt(startValue.toInt(), endValue.toInt())
        } else {
            ValueAnimator.ofFloat(startValue.toFloat(), endValue.toFloat())
        }.apply {
            duration = animationDuration ?: duration
            addUpdateListener {
                text = numberType.formatter.format(it.animatedValue.toString())
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    animationEndListener?.onCountFinish()
                }
            })
            start()
        }
    }

    fun stop() {
        if (::valueAnimator.isInitialized) valueAnimator.end()
    }
}

interface AnimationEndListener {
    fun onCountFinish()
}

sealed class NumberType(val formatter: DecimalFormat) {
    class Integer(format: DecimalFormat = wholeNumberDecimalFormat) : NumberType(format)
    class Decimal(format: DecimalFormat = twoDecimalPlacesFormat) : NumberType(format)
}
