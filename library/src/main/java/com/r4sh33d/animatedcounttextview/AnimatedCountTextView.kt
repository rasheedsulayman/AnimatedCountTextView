/**
 * Copyright (c) Rasheed Sulayman.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.r4sh33d.animatedcounttextview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.appcompat.widget.AppCompatTextView
import com.r4sh33d.animatedcounttextview.NumberType.*

class AnimatedCountTextView(context: Context, attrs: AttributeSet?) :
    AppCompatTextView(context, attrs) {
    private lateinit var valueAnimator: ValueAnimator
    private var numberType: NumberType = Integer()
    private var startValue: Number = 0f
    private var endValue: Number = 0f
    private var animationDuration: Long
    private var animationEndListener: CountEndListener? = null
    private var animationinterpolator: Interpolator = AccelerateDecelerateInterpolator()
    private var prefix = ""
    private var suffix = ""

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AnimatedCountTextView,
            0, 0
        ).run {
            try {
                getString(R.styleable.AnimatedCountTextView_prefix)?.let {
                    prefix = it
                }
                getString(R.styleable.AnimatedCountTextView_suffix)?.let {
                    suffix = it
                }
                getString(R.styleable.AnimatedCountTextView_startWith)?.run {
                    startValue = toFloat()
                }
                getString(R.styleable.AnimatedCountTextView_endWith)?.run {
                    endValue = toFloat()
                }
                getInt(R.styleable.AnimatedCountTextView_numberType, 1).let {
                    numberType = if (it == 1) Integer() else Decimal()
                }
                animationDuration = getInt(R.styleable.AnimatedCountTextView_duration, 4000).toLong()
            } finally {
                recycle()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun start() {
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
                text = "$prefix${numberType.formatter.format(it.animatedValue.toString().toFloat())}$suffix"
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    animationEndListener?.onCountFinish()
                }
            })
            start()
        }
    }

    // ---> Begin Public APIs

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

    fun countEndListener(countEndListener: CountEndListener) {
        this.animationEndListener = countEndListener
    }

    fun prefix(prefix: String) {
        this.prefix = prefix
    }

    fun suffix(suffix: String) {
        this.suffix = suffix
    }

    fun stop() {
        if (::valueAnimator.isInitialized) valueAnimator.end()
    }

    // ---> End Public APIs
}
