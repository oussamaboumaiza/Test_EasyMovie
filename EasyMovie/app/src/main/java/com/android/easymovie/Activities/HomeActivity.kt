package com.android.easymovie.Activities

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.easymovie.Adapters.CustomersAdapter
import com.android.easymovie.R
import com.android.easymovie.Service.CustomerService
import com.android.easymovie.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var customerService: CustomerService
    lateinit var customersAdapter: CustomersAdapter
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = this.getSharedPreferences("app_state", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token","")
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)

        customerService = CustomerService()
        if (token != null) {
            getCustomers(token)
        }

        CustomerService.getCustomerMutable().observe(this, Observer {
            customersAdapter = CustomersAdapter(it.customers)
            binding.recycleViewCustemers.apply {
                layoutManager = LinearLayoutManager(this@HomeActivity)
                adapter = customersAdapter
            }
            customersAdapter.notifyDataSetChanged()
        })
    }

    fun getCustomers(token: String) {
        customerService.getCostomers(null, null, null, null, "Bearer $token")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.itemLogOut -> {
                showLogOutConfirmDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showLogOutConfirmDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation!")
        builder.setMessage("Est-vous sure de vouloir quiter l'appli ?")
        builder.setPositiveButton("Oui") { dialogInterface: DialogInterface, id ->
            val editor = this.getSharedPreferences("app_state", MODE_PRIVATE).edit()
            editor.remove("isAuthentificated")
            editor.apply()
            finish()
        }
        builder.setNegativeButton("Non") { dialogInterface: DialogInterface, id ->
            dialogInterface.dismiss()
        }
        builder.setNeutralButton("Annuler") { dialogInterface: DialogInterface, id ->
            dialogInterface.dismiss()

        }
        val alertDialog: AlertDialog = builder.create()

        alertDialog.setCancelable(false)

        alertDialog.show()

    }
}