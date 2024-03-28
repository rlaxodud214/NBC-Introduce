package com.example.introduce

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.introduce.domain.UserData
import com.example.introduce.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var userData: UserData
    private lateinit var binding: ActivitySignInBinding

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

                binding.user = userData
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        initSignInButton()
        initSignUpButton()
    }

    private fun initSignInButton() {
        binding.btnSignIn.setOnClickListener {
            if (isEmpty(binding.etId) || isEmpty(binding.etPw)) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("userID", binding.etId.text.toString()) // userID
            startActivity(intent)
        }
    }

    private fun isEmpty(editText: EditText) = editText.text.isEmpty()

    private fun initSignUpButton() {
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)

            resultLauncher.launch(intent)
        }
    }
}