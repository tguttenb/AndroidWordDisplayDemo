package com.example.mykfexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mykfexample.models.LfsItem
import com.example.mykfexample.databinding.CardViewDesignBinding
import com.example.mykfexample.models.VarItem
import com.example.mykfexample.utils.OnItemClickListener

class LFAdapter(private val lfsList : ArrayList<LfsItem>,  val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<LFAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding: CardViewDesignBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        init {
            binding.cardMain.setOnClickListener(this)

        }

        override fun onClick(view: View?) {
            if(view!!.id == R.id.card_main) {

                onItemClickListener.onItemClick("click", position = absoluteAdapterPosition)
            }
        }


        fun bind(lf: LfsItem?) {
            binding.lfs = lf
            val lfAdapter = lf?.let { VariationAdapter(it.vars as ArrayList<VarItem>) }
            binding.recyclerVariation.adapter = lfAdapter
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: CardViewDesignBinding = CardViewDesignBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return  lfsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)       {
        holder.bind(lfsList[position])
    }
}