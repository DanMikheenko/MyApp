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
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    companion object {
        private const val REFRESH_RATE = 1_000L
    }

    private lateinit var editText : EditText
    private lateinit var startButton : Button
    private lateinit var timerTextView : TextView

    private var mainThreadHandler = Handler(Looper.getMainLooper())
    private var remainingSeconds: Int = 0
    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editText = findViewById<EditText>(R.id.edit_text_id)
        timerTextView = findViewById<TextView>(R.id.timeTextView)

        startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            val input = editText.text.toString()
            if (input.isNotEmpty()) {
                remainingSeconds = input.toInt()

                runnable = object : Runnable{
                    override fun run() {
                        if (remainingSeconds>0){
                            timerTextView.text = remainingSeconds.toString()
                            remainingSeconds--
                            mainThreadHandler.postDelayed(this, 1000)
                        } else{
                            timerTextView.text = "Done!"
                        }

                    }
                }
                mainThreadHandler.post(runnable!!)

                timerTextView.visibility = View.VISIBLE
            }
        }
    }
}