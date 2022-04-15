package com.tariqul.friends.apiservice

import com.tariqul.friends.business.model.BaseUsersDataModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("?results=10")
    suspend fun getUsers(): BaseUsersDataModel
}

