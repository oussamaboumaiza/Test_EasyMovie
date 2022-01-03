package com.android.easymovie.Data


import com.android.easymovie.Models.Customer
import com.android.easymovie.Models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.Header

class ApiClient() {

    lateinit var apiInterface: ApiInterface
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("$BASE_URL")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         apiInterface = retrofit.create(ApiInterface::class.java)
    }

    companion object {
        private const val BASE_URL = "https://dev-api.easy.movie/v1/"
        private lateinit var INSTANCE: ApiClient

        @JvmStatic
        fun getINSTANCE() : ApiClient{
           if (!::INSTANCE.isInitialized)
               INSTANCE = ApiClient()
            return INSTANCE
        }
    }

    fun Login(email: String, password: String, deviceToken: String?) : Call<User> {
        return  apiInterface.login(email, password, deviceToken)
    }

    fun getCostomers( start: Int?,  limit: Int?,  orderBy: String?,  orderDir: String?,  token: String): Call<Customer> {
        return apiInterface.getClients(start, limit, orderBy, orderDir, token)
    }


}