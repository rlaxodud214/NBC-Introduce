package com.example.introduce.Domain

import java.io.Serializable

// putExtra로 data class를 넘기기 위해선 Serializable(직렬화)를 상속받아야 한다.
data class UserData(
    val name: String,
    val id: String,
    val password: String,
) : Serializable