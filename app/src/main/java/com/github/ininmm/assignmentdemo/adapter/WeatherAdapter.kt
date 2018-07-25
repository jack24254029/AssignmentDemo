package com.github.ininmm.assignmentdemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ininmm.assignmentdemo.R
import kotlinx.android.synthetic.main.item_weather.view.*

/**
 * Created by Michael Lien on 2018/7/25.
 */
class WeatherAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val sampleList = mutableListOf<Pair<Int, String>>(1 to "a", 2 to "b", 3 to "c", 4 to "d", 5 to "e")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)

        return WeatherViewHolder(root)
    }

    override fun getItemCount(): Int {
        return sampleList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is WeatherViewHolder) return
        holder.itemView.ivWeatherData.text = sampleList[position].first.toString()
        holder.itemView.ivWeatherMessage.text = sampleList[position].second
//        holder.itemView.ivWeather.setOnLongClickListener {  }
    }

    private inner class WeatherViewHolder(root: View): RecyclerView.ViewHolder(root)
}