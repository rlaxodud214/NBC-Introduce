package com.example.introduce

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.introduce.Domain.UserData

class SignInActivity : AppCompatActivity() {
    private val editTextID by lazy { findViewById<EditText>(R.id.et_id) }
    private val editTextPW by lazy { findViewById<EditText>(R.id.et_pw) }
    private val signInButton by lazy { findViewById<Button>(R.id.btn_signIn) }
    private val signUpButton by lazy { findViewById<Button>(R.id.btn_signUp) }

    private lateinit var userData: UserData

    /* ref: https://android-developer.tistory.com/7
    startActivityForResult는 Deprecated되고, 대체로 registerForActivityResult를 사용함
    제거된 이유 : 새롭게 연 Activity에서 메모리를 많이 사용할 경우 이전에 열려있던 Activity가 죽어 callBack을 받지 못할 때가 있었다.
    이를 방지하기 위해 새로운 Activity를 실행하는 부분과 callBack 부분을 분리함 -> 이게 registerForActivityResult
     */

    // registerForActivityResult는 FirstFragment.kt 전역 부분에 선언하고 정의해야한다
    // @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK) {
                return@registerForActivityResult
            }

            // 전달 받은 String 데이터를 출력
            if (result.data?.extras?.size() == 1) {
                // 기존에는 getSerializableExtra("key") as T? 를 썻지만
                // 현재는 더 안전하다고 하는 getSerializableExtra("key", T::class.java)가 권장됨
                // 다만 OS: Android 13(API 33)이상의 최신 폰이 요구되므로 쓰일지 모르겠다!! 일단 as 쓰자. 

                // userData = result.data?.getSerializableExtra("userData", UserData::class.java)!!
                userData = result.data?.getSerializableExtra("userData") as UserData

                if (this::userData.isInitialized) {
                    userData.run {
                        editTextID.setText(id)
                        editTextPW.setText(password)
                        Log.d("Debuging userID", id)
                        Log.d("Debuging userPW", password)
                    }

                }
            }
        }

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
            intent.putExtra("userID", editTextID.text.toString()) // userID
            startActivity(intent)
        }
    }

    private fun isEmpty(editText: EditText) = editText.text.isEmpty()

    private fun initSignUpButton() {
        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)

            resultLauncher.launch(intent)
        }
    }
}