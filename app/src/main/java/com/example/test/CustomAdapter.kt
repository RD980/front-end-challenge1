package com.example.test

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val array:ArrayList<Card>, val context: Context):RecyclerView.Adapter<CustomAdapter.DataHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val dataHolder:DataHolder = DataHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_card, parent, false))
        return dataHolder

    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val card:Card = array.get(position)
        holder.txtView1.setText(card.planName)
        holder.txtView2.setText(card.feature1)
        holder.txtView3.setText(card.feature2)
        holder.txtView4.setText(card.feature3)
        holder.button.setOnClickListener {
            val i = Intent(context, PlanActivity::class.java)
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return array.size

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class DataHolder(view:View):RecyclerView.ViewHolder(view){
        val txtView1:TextView = view.findViewById(R.id.prof)
        val txtView2:TextView = view.findViewById(R.id.ft1)
        val txtView3:TextView = view.findViewById(R.id.ft2)
        val txtView4:TextView = view.findViewById(R.id.ft3)
        val button:Button = view.findViewById(R.id.btn1)
    }

}