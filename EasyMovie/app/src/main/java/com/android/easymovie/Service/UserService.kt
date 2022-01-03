package com.android.easymovie.Service

import androidx.lifecycle.MutableLiveData
import com.android.easymovie.Data.ApiClient
import com.android.easymovie.Models.User

import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class UserService {

    companion object{
        var userMutableList: MutableLiveData<User> = MutableLiveData()

        fun  getUserMutable(): MutableLiveData<User>{

            return userMutableList

        }
    }
    fun Login(email: String, password: String, deviceToken: String?)  {
        var user: User?
        ApiClient.getINSTANCE().Login(email, password, deviceToken).enqueue(object :
            Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val result = response.body()
                     user = result
                    userMutableList.postValue(user)
//                    print(user)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
            }
        })

    }


}