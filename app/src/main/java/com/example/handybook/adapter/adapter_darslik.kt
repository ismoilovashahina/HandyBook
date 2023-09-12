package com.example.handybook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.handybook.books.Darslik
import com.example.handybook.databinding.DarslikBinding

class adapter_darslik(val listitem:MutableList<Darslik>):RecyclerView.Adapter<adapter_darslik.Holder>() {

    class Holder(binding: DarslikBinding):RecyclerView.ViewHolder(binding.root){
        val rasm = binding.rasm
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(DarslikBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return listitem.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.rasm.setBackgroundResource(listitem[position].image)
    }
}