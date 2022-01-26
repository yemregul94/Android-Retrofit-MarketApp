package com.works.project_market

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.works.project_market.databinding.ActivityProductDetailBinding
import com.works.project_market.models.OrderData
import com.works.project_market.models.ProductReturn
import com.works.project_market.services.Service
import com.works.project_market.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductDetail : AppCompatActivity() {

    private lateinit var bind: ActivityProductDetailBinding
    lateinit var shared: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shared = getSharedPreferences("user", MODE_PRIVATE)
        val userId = shared.getString("userId", "")
        val data = intent.getSerializableExtra("data") as ProductReturn
        val productId = data.productId

        bind = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)
        title = "Product Detail"

        if (data.imageProduct != null) {
            Glide.with(this).load(data.imageProduct).into(bind.imageProduct)
        } else {
            bind.imageProduct.setImageResource(R.drawable.ic_launcher_foreground)
        }
        bind.textTitle.text = data.productName
        bind.textBrief.text = data.brief
        bind.textDesc.text = data.description
        bind.textPrice.text = data.price + " ₺"

        bind.buttonBuy.setOnClickListener {
            val service = ApiClient.getClient().create(Service::class.java)
            val dataService = service.placeOrder(userId.toString(), productId.toString())
            dataService.enqueue(object : Callback<OrderData> {
                override fun onResponse(call: Call<OrderData>, response: Response<OrderData>) {

                    if (response.isSuccessful) {

                        val ord = response.body()
                        if ( ord != null) {
                            val durum = ord.order!!.get(0)!!.durum
                            val mesaj = ord.order!!.get(0)!!.mesaj
                            if (durum == false){
                                Toast.makeText(this@ProductDetail, mesaj, Toast.LENGTH_SHORT).show()
                            }
                            if (durum == true) {
                                Toast.makeText(this@ProductDetail, mesaj, Toast.LENGTH_SHORT).show()
                                bind.buttonBuy.isEnabled = false
                            }
                        }
                    }else {
                        Log.d("onFailure", "Response Error")
                        Toast.makeText(this@ProductDetail, "Response Hatası", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<OrderData>, t: Throwable) {
                    Log.e("onFailure", "onFailure: $t")
                    Toast.makeText(this@ProductDetail, "İstek Sunucuya İletilemedi!", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}