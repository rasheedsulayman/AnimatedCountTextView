package com.r4sh33d.animatedcounttextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateInterpolator
import com.r4sh33d.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CountEndListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playButton.setOnClickListener {
            countTextView.run {
                countEndListener(this@MainActivity)
                interpolator(AccelerateInterpolator())
                start()
            }
            countTextView.start()

            countTextView.countEndListener(object : CountEndListener{
                override fun onCountFinish() {

                }
            })
        }
    }

    override fun onCountFinish() {
        Log.d("MainActivity", "onCountFinish")
    }
}
