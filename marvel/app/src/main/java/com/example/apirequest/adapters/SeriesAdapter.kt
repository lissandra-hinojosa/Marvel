package com.example.apirequest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apirequest.R
import com.example.apirequest.databinding.ItemSeriesBinding
import com.example.apirequest.interfaces.OnClickSeries
import com.example.apirequest.models.series.Series
import kotlinx.android.synthetic.main.fragment_character_info.view.*
import kotlinx.android.synthetic.main.item_series.view.*

class SeriesAdapter(private val onClickSeries: OnClickSeries): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var series = arrayListOf<Series>()
    private val maxTitleSize = 20
    private lateinit var binding : ItemSeriesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SeriesViewHolder(ItemSeriesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//        return SeriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false))
    }

    override fun getItemCount(): Int {
        return series.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as SeriesViewHolder
        holder.bind(series[position])
    }

    inner class SeriesViewHolder(val binding: ItemSeriesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(series: Series){
            binding.series = series
            binding.executePendingBindings()
        }
    }

    fun setCharacterSeries(seriesList: List<Series>){
        series.clear()
        series.addAll(seriesList)
        notifyDataSetChanged()
    }

}