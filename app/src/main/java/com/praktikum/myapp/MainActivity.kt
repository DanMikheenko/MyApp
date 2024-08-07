package com.praktikum.myapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object{
        private const val REFRESH_RATE = 1_000L
    }
    private var mainThreadHandler : Handler? = null
    private var seconds : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainThreadHandler = Handler(Looper.getMainLooper())
        val editText = findViewById<EditText>(R.id.edit_text_id)

        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            seconds = editText.text as Int
           //  mainThreadHandler.post()
            startButton.isEnabled = false
        }
    }



    private fun refreshTime(){
        val editText = findViewById<EditText>(R.id.edit_text_id)
        val timeTextView = findViewById<TextView>(R.id.timeTextView)
        var seconds = editText.text as Int
        for (i in seconds downTo 0){
            timeTextView.text = seconds as String
            seconds--
        }
        timeTextView.visibility = View.VISIBLE

    }
}