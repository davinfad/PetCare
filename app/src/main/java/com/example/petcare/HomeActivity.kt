package com.example.petcare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        form()
        titipan()
        kondisi()

        logout.setOnClickListener{
            val sharedPreferences=getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor=sharedPreferences.edit()

            editor.putString("STATUS","0")
            editor.apply()

            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    fun form(){
        to_frm_titipan.setOnClickListener(){
            val frm = Intent (this,FormPenitipanActivity::class.java)
            startActivity(frm)
            finish()
        }
    }
    fun titipan(){
        to_dttitipan.setOnClickListener(){
            val dttitipan = Intent(this,DataTitipanActivity::class.java)
            startActivity(dttitipan)
            finish()
        }
    }
    fun kondisi(){
        to_kndisi.setOnClickListener(){
            val kndisi = Intent(this,KondisiActivity::class.java)
            startActivity(kndisi)
            finish()
        }
    }
}
