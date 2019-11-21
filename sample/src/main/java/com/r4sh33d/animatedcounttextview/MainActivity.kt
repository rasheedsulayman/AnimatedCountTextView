package com.r4sh33d.animatedcounttextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton.setOnClickListener {
            textView.run {
                duration(5000)
                startWith(0)
                endWith(100)
                numberType(NumberType.INTEGER)
                play()
            }
        }
    }
}
