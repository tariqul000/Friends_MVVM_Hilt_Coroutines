package com.tariqul.friends.apiservice

import com.tariqul.friends.business.model.BaseUsersDataModel
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    fun getUsers(): Flow<BaseUsersDataModel>
}