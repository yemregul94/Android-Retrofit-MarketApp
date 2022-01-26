package com.works.project_market.services

import com.works.project_market.models.OrderData
import com.works.project_market.models.ProductData
import com.works.project_market.models.UserData
import com.works.project_market.utils.ApiClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("userLogin.php")
    fun userLogin(
        @Query("userEmail") userEmail: String,
        @Query("userPass") userPass: String,
        @Query("ref") ref: String = ApiClient.ref,
        @Query("face") face: String = "no"
    ) : Call<UserData>

    @GET("userRegister.php")
    fun userRegister(
        @Query("userName") userName: String,
        @Query("userSurname") userSurname: String,
        @Query("userPhone") userPhone: String,
        @Query("userMail") userMail: String,
        @Query("userPass") userPass: String,
        @Query("ref") ref: String = ApiClient.ref,
        @Query("face") face: String = "no"
    ) : Call<UserData>

    @GET("product.php")
    fun getProductList(
        @Query("ref") ref: String = ApiClient.ref,
        @Query("start") start: Int = 0
    ) : Call<ProductData>

    @GET("orderForm.php")
    fun placeOrder(
        @Query("customerId") customerId: String,
        @Query("productId") productId: String,
        @Query("ref") ref: String = ApiClient.ref,
        @Query("html") html: Int = 12
    ) : Call<OrderData>

}