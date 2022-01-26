package com.works.project_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import com.works.project_market.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        supportActionBar?.hide()

        object : CountDownTimer(3000, 3000) {
            override fun onTick(p0: Long) {
                Log.d("CountDown", "Sayaç Başladı")
            }

            override fun onFinish() {
                Log.d("CountDown", "Sayaç Durdu")
                bind.imageSplash.visibility = View.GONE
                gotoLogin(bind.root)
            }
        }.start()
    }

    fun gotoLogin(view: View) {
        val i = Intent(this, Login::class.java)
        startActivity(i)
    }

    override fun onRestart() {
        super.onRestart()
        bind.buttonLogin.visibility = View.VISIBLE
    }
}
