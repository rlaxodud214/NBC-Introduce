package com.example.introduce

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    private val textViewID by lazy { findViewById<TextView>(R.id.tv_user_id) }
    private val textViewName by lazy { findViewById<TextView>(R.id.tv_user_name) }
    private val textViewAge by lazy { findViewById<TextView>(R.id.tv_user_age) }
    private val textViewMBTI by lazy { findViewById<TextView>(R.id.tv_user_mbti) }
    private val exitButton by lazy { findViewById<Button>(R.id.btn_exit) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userID = intent.getStringExtra("userID") ?: "전달받은 유저 ID 없음"

        initUserInfo(userID)
        initExitButton()
    }

    private fun initUserInfo(userID: String) {
        tvSetText(textViewID, userID)
        tvSetText(textViewName, "김태영")
        tvSetText(textViewAge, "28")
        tvSetText(textViewMBTI, "INTJ")
    }

    private fun tvSetText(textView: TextView, userID: String) {
        textView.run {
            setText(text.toString() + userID)
        }
    }

    private fun initExitButton() {
        exitButton.setOnClickListener {
            finish()
        }
    }
}