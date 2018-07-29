package com.github.ininmm.assignmentdemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ininmm.assignmentdemo.R
import com.github.ininmm.database.entity.WeatherWeek
import kotlinx.android.synthetic.main.item_weather.view.*

/**
 * Created by Michael Lien on 2018/7/25.
 */
class WeatherAdapter(private var weatherList: MutableList<WeatherWeek>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * 刪除天氣
     */
    var deleteWeatherItem: ((WeatherWeek) -> Unit)? = null

    fun refresh(sampleList: MutableList<WeatherWeek>) {
        this.weatherList = sampleList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)

        return WeatherViewHolder(root)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is WeatherViewHolder) return
        holder.itemView.ivWeatherData.text = weatherList[position].description
        holder.itemView.ivWeather.setOnLongClickListener {
            if (it != null) {
                onLongClick(it, position)
                return@setOnLongClickListener true
            } else {
                return@setOnLongClickListener false
            }
        }
    }

    private fun onLongClick(v: View, position: Int) {
        deleteWeatherItem?.invoke(weatherList[position])
    }

    private inner class WeatherViewHolder(root: View): RecyclerView.ViewHolder(root)
}