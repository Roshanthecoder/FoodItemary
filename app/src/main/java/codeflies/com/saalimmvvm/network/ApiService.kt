package codeflies.com.saalimmvvm.network

import codeflies.com.saalimmvvm.model.PostResultItem
import codeflies.com.saalimmvvm.model.responseClass.home.HomeResponse
import codeflies.com.saalimmvvm.model.responseClass.maintenance.MaintenanceResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {


    @GET("posts")
    suspend fun getPost(): List<PostResultItem>




    @GET("api/home")
    suspend fun getPostHome(
        @Header("Authorization") token: String
    ): HomeResponse


    @GET("api/home/maintenance")
    suspend fun getPostMaintain(
        @Header("Authorization") token: String
    ): MaintenanceResponse



}