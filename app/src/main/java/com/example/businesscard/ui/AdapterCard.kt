package com.example.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscard.model.BusinessCard
import com.example.businesscard.databinding.ItemCardBinding

class AdapterCard : RecyclerView.Adapter<AdapterCard.ViewHolderCard>() {

    var listenerShare: (View) -> Unit = {}

    private var listCard = listOf<BusinessCard>()

    fun setData(list: List<BusinessCard>) {
        this.listCard = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCard {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return ViewHolderCard(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderCard, position: Int) {
        holder.bind(listCard[position])
    }

    override fun getItemCount() : Int = listCard.size


    inner class ViewHolderCard(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BusinessCard) {
            binding.tvName.text = item.name
            binding.tvPhone.text = item.phone
            binding.tvEmail.text = item.email
            binding.tvCompany.text = item.company

            var drawable: ConstraintLayout = binding.clItem
            val itemColor = item.backgroundCustom

            var color:Int = try{
                Color.parseColor(itemColor)
            }catch (e: IllegalAccessException){
                e.printStackTrace()
                Color.parseColor("#FFFFFF")
            }

            drawable.setBackgroundColor(color)
            binding.clItem.setOnClickListener { listenerShare(it) }
        }
    }
}
