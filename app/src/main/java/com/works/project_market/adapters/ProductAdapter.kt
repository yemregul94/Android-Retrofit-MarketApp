package com.works.project_market.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.works.project_market.ProductDetail
import com.works.project_market.R
import com.works.project_market.databinding.ActivityProductsRowBinding.inflate
import com.works.project_market.models.ProductData
import com.works.project_market.models.ProductReturn
import com.works.project_market.services.Service
import com.works.project_market.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductAdapter (private val context: Context, private val arr:ArrayList<ProductReturn>) : BaseAdapter() {

    override fun getCount(): Int {
        return arr.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val bind = inflate(LayoutInflater.from(context), parent, false)
        val item = arr.get(position)

        if (position % 2 == 1)
        {
            bind.rowLayout.setBackgroundColor(Color.parseColor("#F1F1E0FF"))
        }

        if (item.imageProduct != null) {
            Glide.with(context).load(item.imageProduct).into(bind.imageProduct)
        } else {
            bind.imageProduct.setImageResource(R.drawable.ic_launcher_foreground)
        }

        bind.textTitle.text = item.productName
        bind.textBrief.text = item.brief
        bind.textPrice.text = item.price + " ₺"

        bind.rowLayout.setOnClickListener {
            val i = Intent(context, ProductDetail::class.java)
            i.putExtra("data", item)
            context.startActivity(i)
        }

        return bind.root
    }
}
