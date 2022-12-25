package com.example.petcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONArray

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        to_login()

        signup.setOnClickListener(){
            var v_email = email.text.toString()
            var v_username = username.text.toString()
            var v_password = password.text.toString()
            postkeserver(v_email,v_username,v_password)

            val tologin = Intent(this, MainActivity::class.java)
            startActivity(tologin)
        }
    }

    fun postkeserver(data:String, data2:String, data3:String){
        AndroidNetworking.post("http://192.168.1.22/PetCare-User-master/API/pet_care/register.php")
            .addBodyParameter("email", data)
            .addBodyParameter("username", data2)
            .addBodyParameter("password", data3)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {
                }
                override fun onError(anError: ANError?) {
                }
            })
    }

    fun to_login(){
        to_frm_login.setOnClickListener(){
            val tologin = Intent(this, MainActivity::class.java)
            startActivity(tologin)
        }

    }
}
