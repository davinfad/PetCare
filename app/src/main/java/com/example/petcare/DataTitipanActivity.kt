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
import kotlinx.android.synthetic.main.activity_data_titipan.*
import kotlinx.android.synthetic.main.activity_form_penitipan.*
import kotlinx.android.synthetic.main.list_titipan.*
import kotlinx.android.synthetic.main.list_titipan.nama_hewan
import kotlinx.android.synthetic.main.list_titipan.tanggal_penitipan
import org.json.JSONObject

class DataTitipanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_titipan)
        getdatatitipan()
        back()
    }
    fun back(){
        back_f_t.setOnClickListener(){
            val bck = Intent (this,HomeActivity::class.java)
            startActivity(bck)
            finish()
        }
    }
    fun getdatatitipan(){
        val recyclerView = findViewById(R.id.recyclerTitipan) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val titipan=ArrayList<Titipan>()
        AndroidNetworking.get("http://192.168.1.22/PetCare-User-master/API/pet_care/data_titipan.php")
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
                        var isi0=jsonObject.optString("nama_pemilik").toString()
                        var isi1=jsonObject.optString("nama_hewan").toString()
                        var isi2=jsonObject.optString("tanggal_penitipan").toString()
                        var isi3=jsonObject.optString("tanggal_kembali").toString()
                        titipan.add(Titipan("$isi0","$isi1", "$isi2", "$isi3"))
                    }
                    val adapter=AdapterTitipan(titipan)
                    recyclerView.adapter=adapter
                }
                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}
