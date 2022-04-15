package com.tariqul.friends.utill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tariqul.friends.apiservice.ApiHelper
import com.tariqul.friends.business.viewmodel.MainActivityViewModel


class ViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(apiHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}