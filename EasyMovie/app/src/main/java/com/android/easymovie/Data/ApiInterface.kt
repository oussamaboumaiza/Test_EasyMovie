package com.android.easymovie.Data


import com.android.easymovie.Models.Customer
import com.android.easymovie.Models.User
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email") email: String,@Field("password") password: String, @Field("deviceToken") deviceToken: String?): Call<User>


    @GET("secured/customers")
    fun getClients(@Query("start") start: Int?, @Query("limit") limit: Int?, @Query("orderBy") orderBy: String?, @Query("orderDir") orderDir: String?, @Header("AUTHORIZATION") token: String): Call<Customer>

}