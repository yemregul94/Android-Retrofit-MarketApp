package com.works.project_market.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
//https://www.jsonbulut.com/json/userLogin.php?ref=c7c2de28d81d3da4a386fc8444d574f2&userEmail=zehra@mail.com&userPass=12345&face=no
    private val baseUrl = "https://www.jsonbulut.com/json/"

    private var retrofit: Retrofit? = null

    val ref:String = "c7c2de28d81d3da4a386fc8444d574f2"

    fun getClient() : Retrofit {
        if (retrofit == null){
            retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!
    }
}