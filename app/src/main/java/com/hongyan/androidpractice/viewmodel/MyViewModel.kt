package com.hongyan.androidpractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    // 定义需要存储和管理的数据
    val userData: MutableLiveData<User> = MutableLiveData()

    fun fetchUserData() {
        // 模拟从网络或数据库获取数据
        val user = User()
        userData.postValue(user)
    }
}