package com.example.mykfexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mykfexample.databinding.CardViewVariationBinding
import com.example.mykfexample.models.VarItem

class VariationAdapter(private val lfsList : ArrayList<VarItem>) : RecyclerView.Adapter<VariationAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding: CardViewVariationBinding) : RecyclerView.ViewHolder(binding.root)
       {



        fun bind(lf: VarItem?) {
            binding.lfs = lf
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: CardViewVariationBinding = CardViewVariationBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return  lfsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)       {
        holder.bind(lfsList[position])
    }
}