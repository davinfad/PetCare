package com.example.petcare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterKondisi(val KondisiList:ArrayList<Kondisi>): RecyclerView.Adapter<AdapterKondisi.ViewHolder>()  {
    override fun onBindViewHolder(holder: AdapterKondisi.ViewHolder, position: Int) {
        val kondisi: Kondisi=KondisiList[position]
        holder?.nama_pemilik?.text = kondisi.nama_pemilik
        holder?.nama_hewan?.text = kondisi.nama_hewan
        holder?.perawatan_1?.text = kondisi.perawatan_1
        holder?.perawatan_2?.text = kondisi.perawatan_2
        holder?.perawatan_3?.text = kondisi.perawatan_3
        holder?.perawatan_4?.text = kondisi.perawatan_4
    }
    override fun getItemCount(): Int {
        return KondisiList.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterKondisi.ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_kondisi, parent, false)
        return AdapterKondisi.ViewHolder(v)
    }
    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nama_pemilik= itemView.findViewById(R.id.k_nama_pemilik) as TextView
        val nama_hewan= itemView.findViewById(R.id.k_nama_hewan) as TextView
        val perawatan_1= itemView.findViewById(R.id.perawatan_1) as TextView
        val perawatan_2= itemView.findViewById(R.id.perawatan_2) as TextView
        val perawatan_3= itemView.findViewById(R.id.perawatan_3) as TextView
        val perawatan_4= itemView.findViewById(R.id.perawatan_4) as TextView

    }
}