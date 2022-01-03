package com.android.easymovie.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.easymovie.R
import com.android.easymovie.Service.UserService
import com.android.easymovie.databinding.ActivityMainBinding
import com.example.easymoviestest.Utils


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var userService: UserService
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = this.getSharedPreferences("app_state", Context.MODE_PRIVATE)
        val isAuthentificated = sharedPreferences.getBoolean("isAuthentificated",false)
        val email = sharedPreferences.getString("email","")
        if (isAuthentificated)
            Intent(this, HomeActivity::class.java).also {
                it.putExtra("email", email)

                startActivity(it)
            }
        binding.loginBtn.setOnClickListener {
            login()
        }
    }


    fun login(){
        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.pwdEt.text.toString()
            userService = UserService()

            if (isValidEmail(email) && password.length >= 6){
                 userService.Login(email,password,"")
//                if (user != null)
            }else{
                Toast.makeText(this,getString(R.string.errorFileds),Toast.LENGTH_LONG).show()

            }

            UserService.getUserMutable().observe(this, Observer{ data->
                if (data.success) {
                    //enregister dans sharedPreferences le bool isAuthentificated
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("isAuthentificated", true)
                    editor.putString("email", data.email)
                    editor.putInt("uuid", data.user_id)
                    editor.putString("token", data.token)
                    editor.commit()

                    Utils.setGlobalUser(user = data)
                    startActivity(Intent(this, HomeActivity::class.java).apply {
                        putExtra("email", data.email)
                        putExtra("uuid", data.user_id)
                    })
                    finish()
                }else{
                    Toast.makeText(this,"invalid username and/or password",Toast.LENGTH_LONG).show()
                }
                print(data)
            })
        }
    }

    fun isValidEmail(target: String?): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
}