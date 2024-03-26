package com.example.introduce.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.introduce.Domain.UserData
import java.util.regex.Pattern

class UserDataViewModel : ViewModel() {
    private var _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> get() = _userData

    private var _isValidUserData = MutableLiveData<Boolean>()
    val isValidUserData: LiveData<Boolean> get() = _isValidUserData

    private var _validMessage = MutableLiveData<String>()
    val validMessage: LiveData<String> get() = _validMessage

    fun setUserData(userDataObject: UserData, passwordConfirm: String) {
        validate(userDataObject, passwordConfirm)

        if (isValidUserData.value == false) {
            return
        }

        _userData.value = userDataObject
    }

    private fun validate(userDataObject: UserData, passwordConfirm: String) {
        val isNotEmptyAll = userDataObject.isNotEmpty() && passwordConfirm.isNotEmpty()
        val isValidEmail = validateEmail(userDataObject.email)
        val isValidPassword = validatePassword(userDataObject.password, passwordConfirm)

        val isValidFinal = isNotEmptyAll && isValidEmail && isValidPassword

        _isValidUserData.value = isValidFinal
    }

    private fun UserData.isNotEmpty(): Boolean {
        val userData = listOf(name, email, id, password)
        val result = userData.all {
            it.isNotEmpty()
        }

        if (!result) {
            _validMessage.value = "입력되지 않은 정보가 있습니다."
        }

        return result
    }

    private fun validateEmail(email: String): Boolean {
        val pattern = Pattern.compile(EMAIL_REGULAR_EXPRESSION)
        val match = pattern.matcher(email)

        val matchResult = match.find()
        Log.d("UserDataViewModel email 검증", matchResult.toString())

        if (matchResult == false) {
            _validMessage.value = "이메일이 올바르지 않습니다."
        }

        return matchResult
    }

    private fun validatePassword(password: String, passwordConfirm: String): Boolean {
        if (password != passwordConfirm) {
            _validMessage.value = "동일한 비밀번호를 입력해주세요."
            return false
        }

        val pattern = Pattern.compile(PASSWORD_REGULAR_EXPRESSION)
        val match = pattern.matcher(password)

        val matchResult = match.find()
        Log.d("UserDataViewModel pw 검증", matchResult.toString())

        if (matchResult == false) {
            _validMessage.value = "비밀번호 형식이 올바르지 않습니다."
        }

        return matchResult
    }

    companion object {
        /* TODO(Q&A) 1. 실제 현업에서도 정규표현식을 사용할까?
           오타가 입력됐을 때, 인지하기 어려울 것 같아 생긴 의문점

           (추가) 회원가입의 검증에서 어디까지가 Android의 책임?이고, 어디서부터 BackEnd의 책임일까
                  아니면 더블체크를 하려나?
        */
        const val EMAIL_REGULAR_EXPRESSION = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$"
        const val PASSWORD_REGULAR_EXPRESSION = "^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$"
    }
}

