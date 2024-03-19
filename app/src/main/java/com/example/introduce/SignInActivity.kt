package com.example.introduce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    private val editTextID by lazy { findViewById<EditText>(R.id.et_id) }
    private val editTextPW by lazy { findViewById<EditText>(R.id.et_pw) }
    private val signInButton by lazy { findViewById<Button>(R.id.btn_signIn) }
    private val signUpButton by lazy { findViewById<Button>(R.id.btn_signUp) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initSignInButton()
        initSignUpButton()
    }

    private fun initSignInButton() {
        signInButton.setOnClickListener {
            if (isEmpty(editTextID) || isEmpty(editTextPW)) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("id", "empty")
            startActivity(intent)
        }
    }
    private fun isEmpty(editText: EditText) = editText.text.isEmpty()

    private fun initSignUpButton() {
        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}