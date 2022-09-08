package com.example.transhistory.data.api

import com.example.transhistory.data.api.constants.ApiConstants.BASE_URL
import com.example.transhistory.data.api.constants.ApiConstants.TOKEN
import com.example.transhistory.domain.Statement
import com.example.transhistory.domain.StatementResponse
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("myStatement/{limit}/{offset}")
    @SerializedName("items")
    fun listStatement(
        @Path("limit") limit: Int = 10,
        @Path("offset") offset: Int = 0
    ): Call<StatementResponse>

    @GET("myBalance")
    fun getBalance(): Call<Statement>

    companion object {

        private val retrofitService: RetrofitService by lazy {
            val http = OkHttpClient.Builder().addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("token", TOKEN)
                    .build()
                chain.proceed(newRequest)
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(http.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance(): RetrofitService {
            return retrofitService
        }
    }
}