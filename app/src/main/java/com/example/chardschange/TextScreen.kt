package com.example.chardschange

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TextScreen : AppCompatActivity() {

    private var isScrolling = false
    private var scrollSpeed = 5L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_screen)

        val moditedTextView = findViewById<TextView>(R.id.outputText)
        val buttonStartScrolling = findViewById<Button>(R.id.buttonStartScrolling)
        val buttonIncreaseSpeed = findViewById<Button>(R.id.buttonIncreaseSpeed)
        val buttonDecreaseSpeed = findViewById<Button>(R.id.buttonDecreaseSpeed)


        val moditedText = intent.getStringExtra("MODIFIED_TEXT")
        moditedTextView.text = moditedText
        if (moditedText != null) {
            moditedTextView.text = moditedText
        } else {
            moditedTextView.text = "No text received"
        }


        moditedTextView.movementMethod = android.text.method.ScrollingMovementMethod.getInstance()


        buttonStartScrolling.setOnClickListener {
            if (!isScrolling) {
                startScrolling(moditedTextView)
            } else {
                isScrolling = false
            }
        }


        buttonIncreaseSpeed.setOnClickListener {
            if (scrollSpeed > 1) {
                scrollSpeed -= 1
            }
        }


        buttonDecreaseSpeed.setOnClickListener {
            scrollSpeed += 1
        }
    }


    private fun startScrolling(moditedTextView: TextView) {
        isScrolling = true
        val handler = Handler(Looper.getMainLooper())
        val scrollRunnable = object : Runnable {
            override fun run() {
                moditedTextView.scrollBy(0, 1)
                if (isScrolling) {
                    handler.postDelayed(this, scrollSpeed)
                }
            }
        }
        handler.post(scrollRunnable)
    }


    override fun onPause() {
        super.onPause()
        isScrolling = false
    }
}
