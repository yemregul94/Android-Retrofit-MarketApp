package com.works.project_market.models

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("user")
    val user: List<User>
)

data class User(
    @SerializedName("bilgiler")
    val bilgiler: BilgilerUser?,
    @SerializedName("durum")
    val durum: Boolean,
    @SerializedName("mesaj")
    val mesaj: String,
    @SerializedName("kullaniciId")
    val id: String
)

data class BilgilerUser(
    @SerializedName("face")
    val face: String,
    @SerializedName("faceID")
    val faceID: String,
    @SerializedName("userEmail")
    val userEmail: String,
    @SerializedName("userMail")
    val userMail: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("userPhone")
    val userPhone: String,
    @SerializedName("userSurname")
    val userSurname: String
)