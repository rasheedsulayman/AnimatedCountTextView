package com.r4sh33d.animatedcounttextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.r4sh33d.R
import com.r4sh33d.animatedcounttextview.NumberType.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playButton.setOnClickListener {
            textView.run {
                duration(10000)
                startWith(0)
                endWith(100)
                numberType(Integer())
                play()
            }
        }
    }
}
