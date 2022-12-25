package com.example.petcare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterTitipan(val TitipanList:ArrayList<Titipan>): RecyclerView.Adapter<AdapterTitipan.ViewHolder>() {
    override fun onBindViewHolder(holder: AdapterTitipan.ViewHolder, position: Int) {

        val titipan: Titipan=TitipanList[position]
        holder?.nama_pemilik?.text = titipan.nama_pemilik
        holder?.nama_hewan?.text = titipan.nama_hewan
        holder?.tanggal_penitipan?.text = titipan.tanggal_penitipan
        holder?.tanggal_kembali?.text = titipan.tanggal_kembali

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTitipan.ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_titipan, parent, false)
        return AdapterTitipan.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return TitipanList.size
    }
    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nama_pemilik= itemView.findViewById(R.id.nama_pemilik) as TextView
        val nama_hewan= itemView.findViewById(R.id.nama_hewan) as TextView
        val tanggal_penitipan = itemView.findViewById(R.id.tanggal_penitipan) as TextView
        val tanggal_kembali= itemView.findViewById(R.id.tanggal_kembali) as TextView

    }
}