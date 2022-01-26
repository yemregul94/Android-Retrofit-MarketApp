package com.works.project_market

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.works.project_market.databinding.ActivityLoginBinding
import com.works.project_market.models.UserData
import com.works.project_market.services.Service
import com.works.project_market.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private lateinit var bind: ActivityLoginBinding
    lateinit var shared: SharedPreferences
    lateinit var edit: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shared = getSharedPreferences("user", MODE_PRIVATE)
        edit = shared.edit()

        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)
        title = "Login Page"

        if(intent.getStringExtra("email") != null)
        {
            bind.emailText.setText(intent.getStringExtra("email"))
        }
        else
        {
            bind.emailText.setText(shared.getString("email", ""))
        }

        val email = bind.emailText.text
        val password = bind.passwordText.text

        bind.loginButton.setOnClickListener {

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this, "Email Boş Bırakılamaz", Toast.LENGTH_SHORT).show()
                bind.emailText.requestFocus()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(this, "Şifre Boş Bırakılamaz", Toast.LENGTH_SHORT).show()
                bind.passwordText.requestFocus()
                return@setOnClickListener
            }

            val service = ApiClient.getClient().create(Service::class.java)
            val dataService = service.userLogin(email.toString(), password.toString())
            dataService.enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    if (response.isSuccessful) {
                        val u = response.body()
                        if ( u != null) {
                            val durum = u.user.get(0).durum
                            val mesaj = u.user.get(0).mesaj

                            Log.d("OnResponse", "$durum - $mesaj")

                            if (durum == false){
                                Toast.makeText(this@Login, mesaj, Toast.LENGTH_SHORT).show()
                            }
                            if (durum == true) {
                                val userId = u.user.get(0).bilgiler!!.userId
                                val userEmail = u.user.get(0).bilgiler!!.userEmail

                                val intent = Intent(this@Login, Products::class.java)
                                edit.putString("email", userEmail)
                                edit.putString("userId", userId)
                                edit.commit()
                                startActivity(intent)
                                finish()
                            }
                        }
                    }else {
                        Log.d("onFailure", "Response Error")
                        Toast.makeText(this@Login, "Response Hatası", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Log.e("onFailure", "onFailure: $t")
                    Toast.makeText(this@Login, "İstek Sunucuya İletilemedi!", Toast.LENGTH_SHORT).show()

                }
            })
        }

        bind.registerButton.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }

    }
}