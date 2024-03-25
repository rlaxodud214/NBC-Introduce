package com.example.introduce.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.introduce.Domain.UserData

class UserDataViewModel : ViewModel() {
    private var _isValidateUserData = MutableLiveData<Boolean>()
    val isValidateUserData: LiveData<Boolean> get() = _isValidateUserData


    private var _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> get() = _userData

    fun setUserData(
        name: String,
        id: String,
        password: String,
    ) {
        invalidate(name, id, password)
        if (isValidateUserData.value == false) {
            return
        }

        _userData.value = UserData(name, id, password)
    }

    private fun invalidate(
        name: String,
        id: String,
        password: String,
    ) {
        _isValidateUserData.value = name.isNotEmpty() && id.isNotEmpty() && password.isNotEmpty()
    }
}