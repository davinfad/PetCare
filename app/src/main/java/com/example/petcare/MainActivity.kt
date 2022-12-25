package com.example.petcare

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
        val stat=sharedPreferences.getString("STATUS","")

        if (stat=="1"){

            startActivity(Intent(this@MainActivity,HomeActivity::class.java))
            finish()

        }
        else {

            login.setOnClickListener{

                var username=username.text.toString()
                var password=password.text.toString()

                if (username == "" || password == ""
                ) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Alert")
                    builder.setMessage("Username dan Password Tidak Boleh Kosong")
                    builder.setPositiveButton("OK",
                        DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.show()
                } else {
                    postkerserver(username, password)
                }

            }
        }


        tosignup()
    }
    fun tosignup(){
        to_frm_signup.setOnClickListener(){
            val tosignup = Intent(this, SignUpActivity::class.java)
            startActivity(tosignup)
        }

    }

    fun postkerserver(data1:String,data2:String)
    {

        AndroidNetworking.post("http://192.168.1.22/PetCare-User-master/API/pet_care/ceklogin.php")
            .addBodyParameter("username", data1)
            .addBodyParameter("password", data2)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("status"))
                        var statuslogin= jsonObject.optString("status")
                        if (statuslogin=="1"){

                            val sharedPreferences=getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
                            val editor=sharedPreferences.edit()

                            editor.putString("STATUS",statuslogin)
                            editor.apply()

                            startActivity(Intent(this@MainActivity,HomeActivity::class.java))
                            finish()
                            Toast.makeText(applicationContext, "Login berhasil", Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(applicationContext, "Username atau Password Salah ", Toast.LENGTH_SHORT).show()

                        }
                    }



                }

                override fun onError(error: ANError) { // handle error
                }

            })

    }
}
