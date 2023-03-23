package com.hussain.mvpexxampleapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hussain.mvpexxampleapp.databinding.ViewHolderMainBinding
import com.hussain.mvpexxampleapp.network.model.UniversityDTO

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var list = listOf<UniversityDTO>()
    fun addItem(list: List<UniversityDTO>)
    {
        this.list = list
        notifyDataSetChanged()


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MyViewHolder {
        val binding = ViewHolderMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MyViewHolder, position: Int) {
        val binding =holder.viewDataBinding
        val item =this.list[position]

        binding.tvCountry.text= item.country
        binding.tvState.text= item.state_province
        binding.tvName.text=item.name

    }

    override fun getItemCount(): Int {
       return this.list.size
    }

    inner class MyViewHolder(val viewDataBinding:ViewHolderMainBinding):RecyclerView.ViewHolder(viewDataBinding.root)
}