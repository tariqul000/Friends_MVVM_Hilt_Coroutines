package com.tariqul.friends.apiservice

import com.tariqul.friends.business.model.BaseUsersDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override fun getUsers() = flow { emit(apiService.getUsers()) }
}