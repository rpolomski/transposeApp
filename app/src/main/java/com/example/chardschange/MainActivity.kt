package com.example.chardschange

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val inputText = findViewById<EditText>(R.id.songText)
        val buttonUp = findViewById<Button>(R.id.buttonUp)
        val outputText = findViewById<TextView>(R.id.outputText)
        val buttonOk = findViewById<Button>(R.id.okButton)
        val buttonDown = findViewById<Button>(R.id.buttonDown)
        val buttonClear = findViewById<Button>(R.id.clearButton)


        fun replaceSelectedTexts(text: String, phrasesToReplace: Map<String, String>): String {
            val regex = Regex("\\b(${phrasesToReplace.keys.joinToString("|")})\\b")
            return regex.replace(text) { matchResult ->
                phrasesToReplace[matchResult.value] ?: matchResult.value
            }
        }




        var modifiedText = ""

        val mapOf = MapOf()


        buttonUp.setOnClickListener {
            val currentText = if (modifiedText.isEmpty()) inputText.text.toString() else modifiedText
            modifiedText = replaceSelectedTexts(currentText, mapOf.phrasesToReplaceUp)
            outputText.text = modifiedText
        }


        buttonDown.setOnClickListener {
            val currentText = if (modifiedText.isEmpty()) inputText.text.toString() else modifiedText
            modifiedText = replaceSelectedTexts(currentText, mapOf.phrasesToReplaceDown)
            outputText.text = modifiedText
        }


        buttonOk.setOnClickListener {
            val intent = Intent(this, TextScreen::class.java)
            intent.putExtra("MODIFIED_TEXT", modifiedText)
            startActivity(intent)
        }
        buttonClear.setOnClickListener{
            outputText.text = ""
            inputText.setText("")
            modifiedText = ""
        }
    }
}
