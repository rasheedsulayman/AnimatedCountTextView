package com.r4sh33d.animatedcounttextview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.appcompat.widget.AppCompatTextView
import com.r4sh33d.animatedcounttextview.NumberType.*
import java.text.DecimalFormat

class AnimatedCountTextView(context: Context, attrs: AttributeSet?) :
    AppCompatTextView(context, attrs) {
    private lateinit var valueAnimator: ValueAnimator
    private var numberType: NumberType = Integer()
    private var startValue: Number = 0f
    private var endValue: Number = 0f
    private var animationDuration: Long
    private var animationEndListener: AnimationEndListener? = null
    private var animationinterpolator: Interpolator = AccelerateDecelerateInterpolator()

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AnimatedCountTextView,
            0, 0
        ).run {
            try {
                getString(R.styleable.AnimatedCountTextView_startWith)?.run {
                    startValue = toFloat()
                }
                getString(R.styleable.AnimatedCountTextView_endWith)?.run {
                    endValue = toFloat()
                }
                getInt(R.styleable.AnimatedCountTextView_numberType, 1).let {
                    numberType = if (it == 1) Integer() else Decimal()
                }
                animationDuration = getInt(R.styleable.AnimatedCountTextView_duration, 300).toLong()
            } finally {
                recycle()
            }
        }
    }

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
        animationinterpolator = interpolator
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
            duration = animationDuration
            interpolator = animationinterpolator
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
