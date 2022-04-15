package com.tariqul.friends.business.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tariqul.friends.apiservice.ApiHelperImpl
import com.tariqul.friends.apiservice.RetrofitBuilder
import com.tariqul.friends.business.model.BaseUsersDataModel
import com.tariqul.friends.business.view.adopter.UsersListAdapter
import com.tariqul.friends.business.viewmodel.MainActivityViewModel
import com.tariqul.friends.databinding.ActivityMainBinding
import com.tariqul.friends.utill.Status
import com.tariqul.friends.utill.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


//class MainActivity : AppCompatActivity(R.layout.activity_main)


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: UsersListAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
       binding.recyclerView.adapter = adapter
    }



    private fun setupObserver() {
        viewModel.getUsers().observe(this, Observer {
            Log.d("users 2", it.toString())
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding?.recyclerView?.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                    binding?.recyclerView?.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderList(users: BaseUsersDataModel) {

        print("BaseUsersDataModel "+users.result.size)

        adapter.submitList(users.result)

        //adapter.addData(users)
        //adapter.notifyDataSetChanged()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,   ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(MainActivityViewModel::class.java)
    }


}