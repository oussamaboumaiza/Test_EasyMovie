package com.android.easymovie.Service

import androidx.lifecycle.MutableLiveData
import com.android.easymovie.Data.ApiClient
import com.android.easymovie.Models.Customer

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerService {
    companion object{
        var customerMutableList: MutableLiveData<Customer> = MutableLiveData()

        fun  getCustomerMutable(): MutableLiveData<Customer> {

            return customerMutableList

        }
    }
    fun getCostomers( start: Int?,  limit: Int?,  orderBy: String?,  orderDir: String?,  token: String){
        var customer: Customer?

        ApiClient.getINSTANCE().getCostomers(start, limit, orderBy, orderDir, token).enqueue(object :
            Callback<Customer> {
            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    customer = result
                    customerMutableList.postValue(customer)
//                    print(user)
                }
            }

            override fun onFailure(call: Call<Customer>, t: Throwable) {
            }
        })
    }
}