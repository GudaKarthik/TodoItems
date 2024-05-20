package com.example.daggerhilt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter(var list : List<PreRegistraionConsumers>,var click: Click) : RecyclerView.Adapter<NumberAdapter.NumberHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_item,parent,false)
        return NumberHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NumberHolder, position: Int) {
        var data = list[position]
        holder.txtid.text = data.id
        holder.txtnum.text = data.name

        holder.itemView.setOnClickListener {
            click.onclick(data)
        }
    }

    class NumberHolder(view: View) : RecyclerView.ViewHolder(view){
        var txtid : TextView = view.findViewById(R.id.txtid)
        var txtnum : TextView = view.findViewById(R.id.txtnumber)
    }
}

interface Click{
    fun onclick(data : PreRegistraionConsumers)
}