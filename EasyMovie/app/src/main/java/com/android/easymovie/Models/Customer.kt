package com.android.easymovie.Models

data class Customer(
    var total: Int = -1,
    var customers : ArrayList<CustomersJson> = ArrayList()

)

data class CustomersJson(
    var id: Int = -1,
    var name: String = "",
    var logo: String = ""
){

}