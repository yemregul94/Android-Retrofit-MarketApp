package com.works.project_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.works.project_market.databinding.ActivityRegisterBinding
import com.works.project_market.models.UserData
import com.works.project_market.services.Service
import com.works.project_market.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {

    private lateinit var bind: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bind.root)
        title = "Register Page"

        val name = bind.nameText.text
        val surname = bind.surnameText.text
        val phone = bind.phoneText.text
        val email = bind.emailText.text
        val password = bind.passwordText.text



        bind.registerButton.setOnClickListener {

            if(TextUtils.isEmpty(name)){
                Toast.makeText(this, "İsim Boş Bırakılamaz", Toast.LENGTH_SHORT).show()
                bind.nameText.requestFocus()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(surname)){
                Toast.makeText(this, "Soyisim Boş Bırakılamaz", Toast.LENGTH_SHORT).show()
                bind.surnameText.requestFocus()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(phone)){
                Toast.makeText(this, "Telefon Boş Bırakılamaz", Toast.LENGTH_SHORT).show()
                bind.phoneText.requestFocus()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(email)){
                Toast.makeText(this, "Email Boş Bırakılamaz", Toast.LENGTH_SHORT).show()
                bind.emailText.requestFocus()
                return@setOnClickListener
            }
            if(!isEmailValid(email)){
                Toast.makeText(this, "Email Geçersiz", Toast.LENGTH_SHORT).show()
                bind.emailText.requestFocus()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(this, "Şifre Boş Bırakılamaz", Toast.LENGTH_SHORT).show()
                bind.passwordText.requestFocus()
                return@setOnClickListener
            }

                val service = ApiClient.getClient().create(Service::class.java)
                val dataService = service.userRegister(name.toString(), surname.toString(), phone.toString(), email.toString(), password.toString())
                dataService.enqueue(object : Callback<UserData> {
                    override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                        if (response.isSuccessful) {
                            val u = response.body()
                            if ( u != null) {
                                val durum = u.user.get(0).durum
                                val mesaj = u.user.get(0).mesaj
                                val id = u.user.get(0).id
                                Log.d("OnResponse", "$durum - $mesaj - Kullanıcı ID: $id")
                                if (durum == false){
                                    Toast.makeText(this@Register, mesaj, Toast.LENGTH_SHORT).show()
                                }
                                if (durum == true) {
                                    Toast.makeText(this@Register, mesaj, Toast.LENGTH_SHORT).show()

                                    val intent = Intent(this@Register, Login::class.java)
                                    intent.putExtra("email", email.toString())
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        }else {
                            Log.d("onFailure", "Response Error")
                            Toast.makeText(this@Register, "Response Hatası", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<UserData>, t: Throwable) {
                        Log.e("onFailure", "onFailure: $t")
                        Toast.makeText(this@Register, "İstek Sunucuya İletilemedi!", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }


    private fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

