package com.tariqul.friends.business.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tariqul.friends.apiservice.ApiHelper
import com.tariqul.friends.business.model.BaseUsersDataModel
import com.tariqul.friends.utill.Resource
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val apiHelper: ApiHelper,
) : ViewModel() {

    private val users = MutableLiveData<Resource<BaseUsersDataModel>>()
    init {
        fetchUsersList()
    }

    private fun fetchUsersList() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            apiHelper.getUsers()
                .catch { e ->

                    users.postValue(Resource.error(e.toString(), null))
                }
//                .flatMapConcat { usersFromApi ->
//                  more date get
//                }
//                .flowOn(Dispatchers.Default)
                .collect {
                    Log.d("users", Resource.success(it).toString())
                    users.postValue(Resource.success(it))
                }
        }
    }

    fun getUsers(): MutableLiveData<Resource<BaseUsersDataModel>> {
        return users
    }

}