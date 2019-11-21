package com.r4sh33d.animatedcounttextview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.r4sh33d.animatedcounttextview.NumberType.DECIMAL
import com.r4sh33d.animatedcounttextview.NumberType.INTEGER

class AnimatedCountTextView(context: Context, attrs: AttributeSet?) :
    AppCompatTextView(context, attrs) {
    private lateinit var valueAnimator: ValueAnimator
    private var numberType: NumberType = INTEGER
    private var startValue: Number = 0f
    private var endValue: Number = 0f
    private var animationDuration: Long? = null
    private var animationEndListener: AnimationEndListener? = null

    private lateinit var number: Number

    fun startWith(value: Number) {
        numberType = if (value is Int) INTEGER else DECIMAL
        startValue = value
    }

    fun duration(duration: Long){
        animationDuration = duration
    }

    fun endWith(value: Number) {
        endValue = value
    }

    fun numberType(numberType: NumberType) {
        this.numberType = numberType
    }

    fun play() {
        if (::valueAnimator.isInitialized && valueAnimator.isRunning){
            valueAnimator.end()
        }

        valueAnimator = if (numberType == INTEGER) {
            ValueAnimator.ofInt(startValue.toInt(), endValue.toInt())
        } else {
            ValueAnimator.ofFloat(startValue.toFloat(), endValue.toFloat())
        }.apply {

            duration = animationDuration?: duration

            addUpdateListener {
                text = it.animatedValue.toString()
            }

            addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    animationEndListener?.onAnimationEnd()
                }
            })

            start()
        }
    }

    fun stop() {

    }
}

interface AnimationEndListener {
    fun onAnimationEnd();
}
enum class NumberType {
    INTEGER, DECIMAL
}