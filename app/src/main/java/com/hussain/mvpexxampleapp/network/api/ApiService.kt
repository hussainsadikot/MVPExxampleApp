package com.hussain.mvpexxampleapp.network.api

import com.hussain.mvpexxampleapp.network.model.UniversityDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.ResponseCache

interface ApiService {
    @GET("search")
    suspend fun getUniversity(@Query("country") country:String): Response<List<UniversityDTO>>

}