package com.works.project_market


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.works.project_market.adapters.ProductAdapter
import com.works.project_market.databinding.ActivityProductListBinding
import com.works.project_market.models.ProductData
import com.works.project_market.models.ProductReturn
import com.works.project_market.services.Service
import com.works.project_market.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Products : AppCompatActivity() {

    private lateinit var bind: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(bind.root)
        title = "Products Page"

        getProducts()
    }

    private fun getProducts(): ArrayList<ProductReturn> {

        val arr = ArrayList<ProductReturn>()

        val service = ApiClient.getClient().create(Service::class.java)
        val call = service.getProductList()
        call.enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if (response.isSuccessful) {
                    val product = response.body()

                    if (product != null) {
                        val durum = product.products.get(0).durum
                        val mesaj = product.products.get(0).mesaj
                        Log.d("OnResponseData", "$durum - $mesaj")

                        val item = product.products.get(0).bilgiler!!

                        for ((i) in item.withIndex()) {
                            val productImage = item.get(i)!!.images!!.get(0)!!.normal
                            val productName = item.get(i)!!.productName
                            val productBrief = item.get(i)!!.brief
                            val productPrice = item.get(i)!!.price
                            val productDesc = item.get(i)!!.description
                            val productId = item.get(i)!!.productId

                            val data = ProductReturn(
                                productImage,
                                productName,
                                productBrief,
                                productPrice,
                                productDesc,
                                productId
                            )
                            arr.add(data)
                            listData(arr)
                        }
                    }
                }else {
                    Log.d("onFailure", "Response Error")
                    Toast.makeText(this@Products, "Response Hatası", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Log.d("onFailure", "onFailure: $t")
                Toast.makeText(this@Products, "İstek Sunucuya İletilemedi!", Toast.LENGTH_SHORT).show()
            }

        })

        return arr
    }


    fun listData(returnArray: ArrayList<ProductReturn>){
        val adapter = ProductAdapter(this@Products, returnArray)
        bind.productListView.adapter = adapter
    }
}



