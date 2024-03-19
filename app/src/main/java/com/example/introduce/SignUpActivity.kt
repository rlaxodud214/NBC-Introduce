package com.example.introduce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {
    private val editTextName by lazy { findViewById<EditText>(R.id.et_name) }
    private val editTextID by lazy { findViewById<EditText>(R.id.et_id) }
    private val editTextPW by lazy { findViewById<EditText>(R.id.et_pw) }
    private val signUpButton by lazy { findViewById<Button>(R.id.btn_signUp) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initSignUpButton()
    }

    private fun initSignUpButton() {
        signUpButton.setOnClickListener {
            if (isEmpty(editTextName) || isEmpty(editTextID) || isEmpty(editTextPW)) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            // 종료 시, 이전 Activity에 데이터를 넘기는 방법 -> setResult
            // TODO: Option 1번에 있었음
//            intent.putExtra("userID", editTextID.text)
//            setResult(RESULT_OK, intent)
            finish()
        }
    }
    private fun isEmpty(editText: EditText) = editText.text.isEmpty()
}