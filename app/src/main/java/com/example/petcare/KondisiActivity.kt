package com.example.petcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_kondisi.*
import org.json.JSONObject

class KondisiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kondisi)
        getdatakondisi()
        back()
    }
    fun back(){
        back_f_k.setOnClickListener(){
            val bck = Intent (this,HomeActivity::class.java)
            startActivity(bck)
            finish()
        }
    }


    fun getdatakondisi(){
        val recyclerView = findViewById(R.id.recyclerKondisi) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val kondisi=ArrayList<Kondisi>()
        AndroidNetworking.get("http://192.168.1.22/PetCare-User-master/API/pet_care/data_kondisi.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response!!.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("nama_hewan"))
//                        nama_hewan.setText(jsonObject.optString("nama_hewan"))
//                        tanggal_penitipan.setText(jsonObject.optString("tanggal_penitipan"))
//                        t_kembali.setText(jsonObject.optString("tanggal_kembali"))

                        // txt1.setText(jsonObject.optString("shubuh"))
                        var isi1=jsonObject.optString("nama_pemilik").toString()
                        var isi2=jsonObject.optString("nama_hewan").toString()
                        var isi3=jsonObject.optString("perawatan_1").toString()
                        var isi4=jsonObject.optString("perawatan_2").toString()
                        var isi5=jsonObject.optString("perawatan_3").toString()
                        var isi6=jsonObject.optString("perawatan_4").toString()
                        kondisi.add(Kondisi("$isi1", "$isi2", "$isi3","$isi4","$isi5","$isi6"))
                    }
                    val adapter=AdapterKondisi(kondisi)
                    recyclerView.adapter=adapter
                }
                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}
