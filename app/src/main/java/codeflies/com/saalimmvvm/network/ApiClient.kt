package codeflies.com.saalimmvvm.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
  //  private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private const val BASE_URL = "https://marketplaceaws.waayupro.in/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}