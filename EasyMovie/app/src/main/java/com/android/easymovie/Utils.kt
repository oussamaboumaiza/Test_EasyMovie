package com.example.easymoviestest

import androidx.lifecycle.MutableLiveData
import com.android.easymovie.Models.User



class Utils {

    companion object{
         var GlobalUser: User? = null
        @JvmName("getGlobalUser1")
        fun  getGlobalUser(): User? {

            return GlobalUser

        }
        @JvmName("setGlobalUser1")
        fun  setGlobalUser(user: User) {

             GlobalUser = user

        }

    }
}