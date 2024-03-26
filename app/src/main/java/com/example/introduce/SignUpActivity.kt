package com.example.introduce

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.introduce.ViewModel.UserDataViewModel
import com.example.introduce.Domain.UserData

class SignUpActivity : AppCompatActivity() {
    private val editTextName by lazy { findViewById<EditText>(R.id.et_name) }
    private val editTextID by lazy { findViewById<EditText>(R.id.et_id) }
    private val editTextPW by lazy { findViewById<EditText>(R.id.et_pw) }
    private val signUpButton by lazy { findViewById<Button>(R.id.btn_signUp) }

    private val viewModel: UserDataViewModel by lazy {
        ViewModelProvider(this)[UserDataViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initSignUpButton()
        initViewModel()
    }

    private fun initSignUpButton() {
        signUpButton.setOnClickListener {
            viewModel.setUserData(
                editTextName.text.toString(),
                editTextID.text.toString(),
                editTextPW.text.toString()
            )
        }
    }

    private fun initViewModel() {
        viewModel.isValidateUserData.observe(this) { isValid ->
            if (isValid == false) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.userData.observe(this) {
            val resultText = "이름 : ${it.name}, 아이디 : ${it.id}, 비밀번호 : ${it.password}"
            Toast.makeText(this, resultText, Toast.LENGTH_LONG).show()

            exitActivity(it)
        }
    }

    private fun exitActivity(userData: UserData) {
        intent.putExtra("userData", userData)
        setResult(RESULT_OK, intent)

        finish()
    }
}