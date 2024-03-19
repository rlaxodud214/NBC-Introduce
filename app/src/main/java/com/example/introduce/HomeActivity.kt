package com.example.introduce

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    private val textViewID by lazy { findViewById<TextView>(R.id.tv_user_id) }
    private val textViewName by lazy { findViewById<TextView>(R.id.tv_user_name) }
    private val textViewAge by lazy { findViewById<TextView>(R.id.tv_user_age) }
    private val textViewMBTI by lazy { findViewById<TextView>(R.id.tv_user_mbti) }
    private val exitButton by lazy { findViewById<Button>(R.id.btn_exit) }

    private val profileImage by lazy { findViewById<ImageView>(R.id.iv_profileImg) }
    private val images by lazy {
        listOf(
            R.drawable.ic_estp,
            R.drawable.ic_infj,
            R.drawable.ic_infp,
            R.drawable.ic_intj,
            R.drawable.ic_intp,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userID = intent.getStringExtra("userID") ?: "전달받은 유저 ID 없음"

        initProfileImage()
        initUserInfo(userID)
        initExitButton()
    }

    private fun initProfileImage() {
        val randomNumber = Random.nextInt(5) // 0 ~ 4
        Log.d("debuging random Number", randomNumber.toString())

        profileImage.setImageResource(images[randomNumber])
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